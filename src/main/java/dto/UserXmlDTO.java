package dto;

import entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 14.12.12
 * Time: 17:56
 */
@XmlRootElement(name = "User")
public class UserXmlDTO implements Serializable{

    private Integer id;

    private String username;

    private String secondName;

    private String middleName;

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public UserXmlDTO(Integer id, String login) {
        this.id = id;
        this.username = login;
    }

    public UserXmlDTO() {
    }

    public UserXmlDTO(User user){
        this.id=user.getId();
        this.username=user.getUsername();
        this.middleName=user.getMiddleName();
        this.secondName=user.getSecondName();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
