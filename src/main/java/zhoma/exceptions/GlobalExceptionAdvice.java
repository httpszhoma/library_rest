package zhoma.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Error message", ex.getMessage());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleBookNotFoundException(BookNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Error message", ex.getMessage());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(BookTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBookTakenException(BookTakenException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Error message", ex.getMessage());
        return error;
    }

    @ResponseBody
    @ExceptionHandler(BookNotTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBookNotTakenException(BookNotTakenException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Error message", ex.getMessage());
        return error;
    }
}
