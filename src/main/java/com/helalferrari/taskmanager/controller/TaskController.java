package com.helalferrari.taskmanager.controller;

import com.helalferrari.taskmanager.dto.TaskCreateDto;
import com.helalferrari.taskmanager.dto.TaskUpdateDto;
import com.helalferrari.taskmanager.model.Task;
import com.helalferrari.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateDto taskCreateDto) {
        return ResponseEntity.ok(taskService.save(taskCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody TaskUpdateDto taskUpdateDto) {
        return taskService.update(id, taskUpdateDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable UUID id) {
        return taskService.completeTask(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/uncomplete")
    public ResponseEntity<Task> uncompleteTask(@PathVariable UUID id) {
        return taskService.uncompleteTask(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
