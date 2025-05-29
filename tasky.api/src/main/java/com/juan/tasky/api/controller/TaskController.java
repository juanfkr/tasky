package com.juan.tasky.api.controller;

import com.juan.tasky.api.model.Task;
import com.juan.tasky.api.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @PostMapping("/post")
    public Task postTask(@RequestBody Task task) {
        return taskService.save(task);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Task> updateName(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.update(id, task.getName());
        return ResponseEntity.ok(updatedTask);
    }
}
