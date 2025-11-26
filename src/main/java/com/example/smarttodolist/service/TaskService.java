package com.example.smarttodolist.service;

import com.example.smarttodolist.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(Long id) {
        taskList.removeIf(task -> task.getId().equals(id));
    }
    public List<Task> findTasks(){
        return taskList;
    }
}
