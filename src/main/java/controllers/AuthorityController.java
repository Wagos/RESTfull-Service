package controllers;

import constants.Constants;
import dto.AuthorityList;
import entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.AuthorityService;
import services.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 13.12.12
 * Time: 10:07
 */
@Controller
//     request: /roles
@RequestMapping(value = Constants.PATH_SEPARATOR+Constants.ROLES)
@Secured(value = "ROLE_ADMIN")
public class AuthorityController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public void addPrivilege(@Valid Authority authority){
//        authorityService.add(authority);
//    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public AuthorityList addPrivilege(){
        return new AuthorityList(authorityService.getAuthorities());
    }

    //     request: /roles/{userId}
    @RequestMapping(value = Constants.PATH_SEPARATOR + Constants.USER_ID,method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addAuthorityToUser(@PathVariable Integer userId,@RequestParam String authority){
        userService.addAuthority(authorityService.getByName(authority),userId);
    }
}
