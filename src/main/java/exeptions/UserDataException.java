package exeptions;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 17.12.12
 * Time: 8:57
 */
public class UserDataException extends RuntimeException {
    public UserDataException(String message) {
        super(message);
    }

    public UserDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
