package dto;

import entity.Authority;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 13.12.12
 * Time: 12:25
 */
@XmlRootElement
public class AuthorityList {
    List<Authority> authorities;

    public AuthorityList() {
    }

    public AuthorityList(List<Authority> authorities) {

        this.authorities = authorities;
    }

    public List<Authority> getAuthorities() {

        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
