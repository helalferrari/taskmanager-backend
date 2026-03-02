package com.helalferrari.taskmanager.controller;

import com.helalferrari.taskmanager.model.Task;
import com.helalferrari.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasksByUserId(@RequestParam UUID userId) {
        return taskService.findByUserId(userId);
    }
}
