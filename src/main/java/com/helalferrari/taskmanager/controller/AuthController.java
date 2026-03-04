package com.helalferrari.taskmanager.controller;

import com.helalferrari.taskmanager.dto.LoginRequest;
import com.helalferrari.taskmanager.dto.LoginResponse;
import com.helalferrari.taskmanager.model.User;
import com.helalferrari.taskmanager.repository.UserRepository;
import com.helalferrari.taskmanager.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        System.out.println("Tentativa de login para: " + request.getEmail());
        return userRepository.findByEmail(request.getEmail())
                .map(user -> {
                    boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
                    System.out.println("Usuário encontrado no banco. Hash: " + user.getPassword());
                    System.out.println("Senha da requisição: " + request.getPassword());
                    System.out.println("Resultado da comparação: " + matches);
                    
                    if (matches) {
                        String token = jwtService.generateToken(user.getId(), user.getEmail());
                        LoginResponse response = LoginResponse.builder()
                                .token(token)
                                .user(LoginResponse.UserDto.builder()
                                        .id(user.getId())
                                        .email(user.getEmail())
                                        .build())
                                .build();
                        return ResponseEntity.ok(response);
                    }
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos");
                })
                .orElseGet(() -> {
                    System.out.println("Usuário não encontrado: " + request.getEmail());
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos");
                });
    }
}
