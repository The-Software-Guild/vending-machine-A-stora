package Service;

public class SelectionNotInListException extends Exception{
    public SelectionNotInListException(String message) {
        super(message);
    }

    public SelectionNotInListException(String message, Throwable cause) {
        super(message, cause);
    }
}

