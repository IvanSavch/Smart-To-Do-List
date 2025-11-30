package com.example.smarttodolist.service;

import com.example.smarttodolist.dao.InMemoryTaskDao;
import com.example.smarttodolist.dao.InMemoryTaskDaoImpl;
import com.example.smarttodolist.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final InMemoryTaskDaoImpl inMemoryTaskDao = new InMemoryTaskDao();

    public void addTask(Task task) {
        inMemoryTaskDao.save(task);
    }

    public void deleteTask(Long id) {
        inMemoryTaskDao.delete(id);
    }
    public List<Task> findTasks(){
      return inMemoryTaskDao.find();
    }

    public void update(Task task){
        inMemoryTaskDao.update(task);
    }
    public void complete(Long id){
        inMemoryTaskDao.complete(id);
    }
    public Task findById(Long id){
        return inMemoryTaskDao.findById(id);
    }
}
