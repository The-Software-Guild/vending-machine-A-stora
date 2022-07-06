package Service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachineDaoException;
import dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Mock
    private VendingMachineDao dao;
    @Mock
    private Item item;
    @Mock
    private VendingMachineAuditDao auditdao;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void decrementStockTest() throws VendingMachineDaoException, NoItemInInventoryException {
        Service service = new Service(dao, auditdao);
        Mockito.when(dao.getItem(Mockito.anyString())).thenReturn(item);
        Mockito.when(item.getStock()).thenReturn(1);
        String selection = "string";
        try{
            service.decrementStock(selection);
        } catch(NoItemInInventoryException e){
            fail();
        }
    }

    @Test
    void checkExists() throws VendingMachineDaoException {
        Service service = new Service(dao, auditdao);
        Mockito.when(dao.getItem(Mockito.anyString())).thenReturn(null);
        String selection = "string";
        assertThrows(SelectionNotInListException.class, () -> {
            service.checkExists(selection);
        });
    }

    @Test
    void newMoneyTest() throws VendingMachineDaoException {
        Service service = new Service(dao, auditdao);
        BigDecimal money = new BigDecimal("5.00");
        BigDecimal price = new BigDecimal("1.00");
        Mockito.when(dao.getItem(Mockito.anyString())).thenReturn(item);
        Mockito.when(item.getPrice()).thenReturn(price);
        String selection = "string";
        assertEquals(service.newMoney(money, selection), new BigDecimal("4.00"));
    }

    @Test
    void checkFundsTest() throws VendingMachineDaoException {
        Service service = new Service(dao, auditdao);
        BigDecimal money = new BigDecimal("1.00");
        BigDecimal price = new BigDecimal("2.00");
        Mockito.when(dao.getItem(Mockito.anyString())).thenReturn(item);
        Mockito.when(item.getPrice()).thenReturn(price);
        String selection = "string";
        assertThrows(InsufficientFundsException.class, () -> {
            service.checkFunds(selection, money);
        });
    }


    @Test
    void returnChangeTest() {
        Service service = new Service(dao, auditdao);
        BigDecimal money = new BigDecimal("3.88");
        List<Integer> change = service.returnChange(money);
        List<Integer> coins = new ArrayList<>(Arrays.asList(1,1,1,1,1,1,1,1));
        assertEquals(change, coins);
    }
}