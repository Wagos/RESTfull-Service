package services;

import constants.Constants;
import dto.UserList;
import dto.UserXmlDTO;
import entity.Authority;
import entity.User;
import exeptions.ExceptionsCheker;
import exeptions.UserDataException;
import exeptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 05.12.12
 * Time: 12:38
 */
@Service
@Transactional()
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //----------------SEARCH OPERATIONS-------------------------------------------------------------------


    public UserList getAllUsers(){
        return new UserList(userRepository.findAll());
    }

    public UserXmlDTO getUser(Integer id){
        User user=userRepository.findOne(id);
        return new UserXmlDTO(user.getId(),user.getUsername());
    }

    public UserList getCurrentUserFriends() {
        List<User> users=userRepository.findFriends(CurrentUserHandler.getCurrentUser().getId());
        return new UserList(users);
    }

    public UserList getUsersByNames(String name,String secondName,String middleName){
        return new UserList(userRepository.findByNameAndSecondNameAndMiddleName(name, secondName, middleName));
    }

    public User getUserByUsername(String login){
        return userRepository.findByUsername(login);
    }

    public List<Authority> getUserPrivileges(Integer userId){
        return userRepository.findAuthoritiesById(userId);
    }

    public Long getUsersCount(){
        return userRepository.count();
    }


    //---------USER MODIFICATION OPERATION------------------------------------------


    public void saveUser(User user){
        Integer id=user.getId();
        if(id==null || !userRepository.exists(id)){
            Set<Authority> authorities=new HashSet<>();
            authorities.add(new Authority("ROLE_USER"));
            user.setAuthorities(authorities);
        }
        else{
            if(CurrentUserHandler.getAuthorities().contains(new Authority("ROLE_DEFAULT"))){
                throw new UserDataException(Constants.INCORRECT_USER_DATA);
            }
        }
        userRepository.save(user);
    }

    public void addFriend(Integer friendId){
//        userRepository.insertFriendById(userId, friendId);
        User user=CurrentUserHandler.getCurrentUser();
        User friend=userRepository.findOne(friendId);

        ExceptionsCheker.checkUserFound(friend);

        user.addFriend(friend);
        friend.addFriend(user);

        userRepository.save(user);
        //userRepository.save(friend);
    }

    public void addAuthority(Authority authority, Integer userId){
        User user=userRepository.findOne(userId);

        ExceptionsCheker.checkUserFound(user);

        Set<Authority> userAuthorities =user.getAuthorities();
        userAuthorities.add(authority);

        userRepository.save(user);
    }

    public void updateCurrentUser(User user){
        user.setId(CurrentUserHandler.getCurrentUser().getId());
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user=userRepository.findOne(id);
        ExceptionsCheker.checkUserFound(user);
        userRepository.delete(user);
    }


    public void deleteUserFriend(Integer friendId) {

        User user=CurrentUserHandler.getCurrentUser();
        User friend=userRepository.findOne(friendId);

        ExceptionsCheker.checkUserFound(friend);
        user.removeFriend(friend);
        friend.removeFriend(user);

        userRepository.save(user);
        userRepository.save(friend);
    }

}
