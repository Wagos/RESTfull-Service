package repositories;

import entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 13.12.12
 * Time: 10:16
 */
public interface AuthorityRepository extends JpaRepository<Authority,String>{
}
