package controllers;

import constants.Constants;
import dto.UserXmlDTO;
import entity.User;
import dto.UserList;
import exeptions.ExceptionsCheker;
import exeptions.UserDataException;
import exeptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 05.12.12
 * Time: 12:33
 */
@Controller
@RequestMapping(value = Constants.PATH_SEPARATOR+Constants.USERS)
@Secured(value = "ROLE_USER")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public UserList getUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET,params = "count")
    @ResponseBody public String getUsersCount(){
        return userService.getUsersCount().toString();
    }

    @RequestMapping(method = RequestMethod.GET,params = "name")
    @ResponseBody
    public UserList getUsersWithMask(@RequestParam String name,
                                     @RequestParam(required = false) String secondName,
                                     @RequestParam(required = false) String middleName){
        return userService.getUsersByNames(name,secondName,middleName);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUser(@Valid User user,BindingResult bindingResult){
        ExceptionsCheker.checkUserDataBinding(bindingResult);
        userService.updateCurrentUser(user);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public UserXmlDTO getUser(@PathVariable("id") Integer id){
       return userService.getUser(id);
    }

//    @RequestMapping(method = RequestMethod.GET,params = "username")
//    @ResponseBody
//    public UserXmlDTO getUsersByUsername(@RequestParam String username){
//        return new UserXmlDTO(userService.getUserByUsername(username));
//    }
    ///----------ADMIN ACTIONS HANDLERS---------------------------------------------------------------------------------

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    @Secured(value = {"ROLE_ADMIN","ROLE_DEFAULT"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addUser(@Valid User user,BindingResult bindingResult){
        ExceptionsCheker.checkUserDataBinding(bindingResult);
        userService.saveUser(user);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured(value = "ROLE_ADMIN")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }


    /////----------EXCEPTIONS HANDLER----------------------------------------------------------------------------------
    @ExceptionHandler(value = {UserDataException.class, UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void sendError(){
    }
}
