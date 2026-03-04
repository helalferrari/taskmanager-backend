package com.helalferrari.taskmanager.service;

import com.helalferrari.taskmanager.dto.UserUpdateDto;
import com.helalferrari.taskmanager.exception.EmailAlreadyExistsException;
import com.helalferrari.taskmanager.model.User;
import com.helalferrari.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Este e-mail já está cadastrado.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> update(UUID id, UserUpdateDto dto) {
        return userRepository.findById(id).map(user -> {
            if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
                if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                    throw new EmailAlreadyExistsException("Este e-mail já está em uso por outro usuário.");
                }
                user.setEmail(dto.getEmail());
            }
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            return userRepository.save(user);
        });
    }
}
