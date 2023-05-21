package webapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapi.models.User;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
