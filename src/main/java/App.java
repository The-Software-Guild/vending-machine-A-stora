import Controller.VendingMachineController;
import Service.Service;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        Service myService = new Service(myDao);
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
    }
}
