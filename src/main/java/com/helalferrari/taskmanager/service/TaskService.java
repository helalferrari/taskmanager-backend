package com.helalferrari.taskmanager.service;

import com.helalferrari.taskmanager.model.Task;
import com.helalferrari.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findByUserId(UUID userId) {
        return taskRepository.findByUserId(userId);
    }
}
