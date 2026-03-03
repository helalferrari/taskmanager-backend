package com.helalferrari.taskmanager.controller;

import com.helalferrari.taskmanager.dto.UserUpdateDto;
import com.helalferrari.taskmanager.model.User;
import com.helalferrari.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(id, userUpdateDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
