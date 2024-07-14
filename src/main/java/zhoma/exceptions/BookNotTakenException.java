package zhoma.exceptions;

public class BookNotTakenException extends RuntimeException{
    public BookNotTakenException(String message) {
        super(message);
    }
}
