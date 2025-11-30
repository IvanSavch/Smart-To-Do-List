package com.example.smarttodolist.dao;

import com.example.smarttodolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskDao implements InMemoryTaskDaoImpl {

    private final List<Task> taskList = new ArrayList<>();

    @Override
    public void save(Task task) {
        taskList.add(task);
    }

    @Override
    public void delete(Long id) {
        taskList.removeIf(task -> task.getId().equals(id));
    }

    @Override
    public List<Task> find() {
        return taskList;
    }

    @Override
    public void update(Task task) {
        for (Task t : taskList) {
            if (t.getId().equals(task.getId())) {
                t.setName(task.getName());
                t.setDescription(task.getDescription());
                t.setCompleted(task.isCompleted());
            }
        }
    }

    @Override
    public void complete(Long id) {
        for (Task t : taskList) {
            if (t.getId().equals(id)) {
                t.setCompleted(true);
            }
        }
    }

    @Override
    public Task findById(Long id) {
        return taskList.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow();
    }
}
