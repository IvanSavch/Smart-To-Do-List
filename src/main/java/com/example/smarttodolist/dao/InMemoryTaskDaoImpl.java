package com.example.smarttodolist.dao;

import com.example.smarttodolist.model.Task;

import java.util.List;

public interface InMemoryTaskDaoImpl {
    void save(Task task);
    void delete(Long id);
    List<Task> find();
    void update(Task task);
    void complete(Long id);
    Task findById(Long id);
}
