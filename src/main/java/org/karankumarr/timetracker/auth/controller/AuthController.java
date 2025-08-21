package org.karankumarr.timetracker.auth.controller;

import org.karankumarr.timetracker.auth.service.AuthService;
import org.karankumarr.timetracker.user.dto.LoginRequest;
import org.karankumarr.timetracker.user.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token); // avoid exposing password
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String name = registerRequest.getName();
        String confirmPassword = registerRequest.getConfirmPassword();

        if (email == null || password == null || name == null || confirmPassword == null) {
            return ResponseEntity.badRequest().body("All fields (email, password, confirmPassword, name) are required.");
        }

        try {
            String token = authService.createUser(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getConfirmPassword(), registerRequest.getName());
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }

}
