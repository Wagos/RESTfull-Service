package services;

import entity.Authority;
import entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 14.12.12
 * Time: 16:40
 */
public class CurrentUserHandler {
    public static User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) principal;
    }

    public static List<Authority> getAuthorities(){
        return (List<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
