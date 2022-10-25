package id.co.bca.spring.hellospring.repository;

import id.co.bca.spring.hellospring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}



