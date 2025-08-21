package org.karankumarr.timetracker.user.service;

import jakarta.persistence.EntityNotFoundException;
import org.karankumarr.timetracker.user.entity.User;
import org.karankumarr.timetracker.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return this.userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User with id " + id + " not found"));
    }


    public User updateUser(Integer id, Map<String, Object> updates) {
                Optional<User> optionalUser = Optional.ofNullable(getUserById(id));
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("No user found.");
        }

        User user = optionalUser.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> user.setName((String) value);
                case "email" -> user.setEmail((String) value);
                case "password" -> user.setPassword(encoder.encode((String) value));
            }
        });

        this.userRepository.save(user);

        return user;
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

}
