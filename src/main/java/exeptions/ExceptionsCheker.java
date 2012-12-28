package exeptions;

import constants.Constants;
import entity.User;
import org.springframework.validation.BindingResult;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 27.12.12
 * Time: 14:43
 */
public class ExceptionsCheker {

    public static void checkUserFound(User user){
        if(user==null){
            throw new UserNotFoundException(Constants.USER_NOT_FOUND);
        }
    }

    public static void checkUserDataBinding(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new UserDataException(Constants.INCORRECT_USER_DATA);
        }
    }
}
