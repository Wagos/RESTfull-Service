package dto;

import entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 11.12.12
 * Time: 9:33
 */
@XmlRootElement
public class UserList{

    private List<UserXmlDTO> users=new ArrayList<>();

    public UserList(List<User> userList){
        for (User user:userList){
            users.add(new UserXmlDTO(user));
        }
    }

    public List<UserXmlDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserXmlDTO> users) {
        this.users = users;
    }

    public UserList(){

    }

    public void add(UserXmlDTO userXmlDTO){
        users.add(userXmlDTO);
    }

}
