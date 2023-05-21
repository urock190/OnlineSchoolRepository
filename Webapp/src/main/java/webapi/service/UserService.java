package webapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapi.dao.UserRepository;
import webapi.dto.UserDto;
import webapi.models.Role;
import webapi.models.User;
import webapi.validation.UserAlreadyExistException;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException{
        if (usernameExists(accountDto.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that username: " + accountDto.getUsername());
        }
        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setUsername(accountDto.getUsername());
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    private boolean usernameExists(String username) {
        return findUserByUsername(username) != null;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
