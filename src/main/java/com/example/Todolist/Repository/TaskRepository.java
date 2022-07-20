package com.example.Todolist.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Todolist.Model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>  {
    
}
