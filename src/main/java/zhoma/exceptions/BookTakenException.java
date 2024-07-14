package zhoma.exceptions;

public class BookTakenException extends RuntimeException{
    public BookTakenException(String message) {
        super(message);
    }
}
