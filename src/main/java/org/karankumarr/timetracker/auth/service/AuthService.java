package org.karankumarr.timetracker.auth.service;

import org.karankumarr.timetracker.user.entity.User;
import org.karankumarr.timetracker.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }

    public String createUser(String email, String password,String confirmPassword, String name) {
        boolean userExists = userRepository.findByEmail(email).isPresent();

        if(userExists) {
            throw new IllegalArgumentException("User already exists with this email.");
        }

        if(!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        String encodedPassword = encoder.encode(password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setName(name);
        userRepository.save(user);

        return jwtService.generateToken(user.getEmail());
    }

}
