package id.co.bca.spring.hellospring.service;

import id.co.bca.spring.hellospring.model.MyUserDetail;
import id.co.bca.spring.hellospring.model.User;
import id.co.bca.spring.hellospring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("No user found!");
        }
        return new MyUserDetail(user);
    }
}

