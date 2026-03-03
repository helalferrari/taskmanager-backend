package com.helalferrari.taskmanager.service;

import com.helalferrari.taskmanager.dto.TaskCreateDto;
import com.helalferrari.taskmanager.dto.TaskUpdateDto;
import com.helalferrari.taskmanager.model.Task;
import com.helalferrari.taskmanager.model.User;
import com.helalferrari.taskmanager.repository.TaskRepository;
import com.helalferrari.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> findByUserId(UUID userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task save(TaskCreateDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Task task = Task.builder()
                .title(dto.getTitle())
                .user(user)
                .completed(false)
                .deleted(false)
                .build();

        return taskRepository.save(task);
    }

    public Optional<Task> update(UUID id, TaskUpdateDto dto) {
        return taskRepository.findById(id).map(task -> {
            // Validação: Somente o dono da tarefa pode alterá-la
            if (!task.getUser().getId().equals(dto.getUserId())) {
                throw new RuntimeException("Você não tem permissão para alterar esta tarefa.");
            }

            if (dto.getTitle() != null) {
                task.setTitle(dto.getTitle());
            }
            if (dto.getCompleted() != null) {
                task.setCompleted(dto.getCompleted());
            }
            if (dto.getDeleted() != null) {
                task.setDeleted(dto.getDeleted());
            }
            return taskRepository.save(task);
        });
    }
}
