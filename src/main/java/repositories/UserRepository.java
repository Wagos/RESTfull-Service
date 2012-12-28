package repositories;

import dto.UserXmlDTO;
import entity.Authority;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 05.12.12
 * Time: 12:34
 */
public interface UserRepository extends JpaRepository <User,Integer> {

    public List<User> findByNameAndSecondNameAndMiddleName(String name, String secondName, String middleName);

    public User findByUsername(String login);

    @Query(value = "select friends from User u where u.id=?1")
    public List<User> findFriends(Integer id);

    @Query(value = "select authorities from User u where u.id=?1")
    public List<Authority> findAuthoritiesById(Integer id);

//    @Modifying
//    @Query(value = "update User u set u.authorities=?1 where u.id=?2")
//    public void addAuthority(Set<Authority> authorities, Integer id);

}
