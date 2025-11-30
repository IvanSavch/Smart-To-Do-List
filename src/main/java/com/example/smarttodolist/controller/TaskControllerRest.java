package com.example.smarttodolist.controller;

import com.example.smarttodolist.model.Task;
import com.example.smarttodolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Task")
public class TaskControllerRest {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>>findTasks(){
        return ResponseEntity.ok(taskService.findTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping()
    public void update(@RequestBody Task task){
        taskService.update(task);
    }
    @PutMapping("/{id}/complete")
    public void completeTask(@PathVariable Long id){
        taskService.complete(id);
    }

}
