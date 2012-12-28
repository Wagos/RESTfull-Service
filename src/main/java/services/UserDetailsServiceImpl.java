package services;

import entity.User;
import exeptions.ExceptionsCheker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 11.12.12
 * Time: 10:31
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user= userService.getUserByUsername(s);
        ExceptionsCheker.checkUserFound(user);
        return user;
    }
}
