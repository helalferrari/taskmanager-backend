package com.helalferrari.taskmanager.controller;

import com.helalferrari.taskmanager.dto.RegisterResponse;
import com.helalferrari.taskmanager.dto.UserUpdateDto;
import com.helalferrari.taskmanager.model.User;
import com.helalferrari.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        RegisterResponse response = RegisterResponse.builder()
                .message("Usuário registrado com sucesso!")
                .user(RegisterResponse.UserDto.builder()
                        .id(savedUser.getId())
                        .email(savedUser.getEmail())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(id, userUpdateDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
