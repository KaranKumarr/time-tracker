package org.karankumarr.timetracker.user.controller;

import org.karankumarr.teerly.user.dto.UserResponse;
import org.karankumarr.teerly.user.entity.User;
import org.karankumarr.teerly.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    @TODO remove this function after testing
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getPassword());

        return ResponseEntity.ok(userResponse);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id,
                                                   @RequestBody Map<String, Object> updates) {
        User user = userService.updateUser(id, updates);

        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getEmail());

        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



}
