package exeptions;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 27.12.12
 * Time: 10:16
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
