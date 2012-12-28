package services;

import entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AuthorityRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 13.12.12
 * Time: 10:17
 */
@Service
@Transactional
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    public boolean checkExist(String privilege){
        return authorityRepository.exists(privilege);
    }

    public void add(Authority authority){
        authorityRepository.save(authority);
    }

    public Authority getByName(String authority){
        return authorityRepository.findOne(authority);
    }

    public List<Authority> getAuthorities(){
        return authorityRepository.findAll();
    }
}
