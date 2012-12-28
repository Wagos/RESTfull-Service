package controllers;

import constants.Constants;
import dto.UserList;
import dto.UserXmlDTO;
import exeptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.UserService;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 05.12.12
 * Time: 13:37
 */
@Controller
@RequestMapping(value = Constants.PATH_SEPARATOR +Constants.PATH_SEPARATOR+Constants.FRIENDS)
@Secured(value = "ROLE_USER")
public class FriendController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addFriend(@RequestParam Integer friendId){
        userService.addFriend(friendId);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public UserList getUserFriends(){
        return userService.getCurrentUserFriends();
    }

    @RequestMapping(value = "/{friendId}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteFriend(@PathVariable Integer friendId){
        userService.deleteUserFriend(friendId);
    }

    @RequestMapping(value = "/{friendId}",method = RequestMethod.GET)
    @ResponseBody
    public UserXmlDTO getUserFriend(@PathVariable Integer friendId){
        return userService.getUser(friendId);
    }

    @ExceptionHandler(value = { UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void sendError(){
    }
}
