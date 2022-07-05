package Service;

public class NoItemInInventoryException extends Exception{
    public NoItemInInventoryException(String message) {
        super(message);
    }

    public NoItemInInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
