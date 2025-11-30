package com.example.smarttodolist.controller;

import com.example.smarttodolist.model.ErrorResponse;
import com.example.smarttodolist.model.Task;
import com.example.smarttodolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Task")
public class TaskControllerRest {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> findTasks() {
        return ResponseEntity.ok(taskService.findTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            taskService.findById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setTitle("Invalid data");
            errorResponse.setStatus("400");
            Map<String,String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(),fieldError.getDefaultMessage());
                errorResponse.setErrors(errors);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping()
    public void update(@RequestBody Task task) {
        taskService.update(task);
    }

    @PutMapping("/{id}/complete")
    public void completeTask(@PathVariable Long id) {
        taskService.complete(id);
    }

}
