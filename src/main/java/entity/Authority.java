package entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 11.12.12
 * Time: 10:45
 */
@Entity
@Table(name = "privilegies")
@XmlRootElement
public class Authority implements GrantedAuthority {
    @Id
    @Column(name = "name")
    private String authority;

    @ManyToMany(cascade = CascadeType.REMOVE,mappedBy = "authorities")
    private Set<User> users;

    public Authority() {

    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String name) {
        this.authority = name;
    }


}
