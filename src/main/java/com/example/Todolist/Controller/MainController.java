package com.example.Todolist.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Todolist.Model.Task;
import com.example.Todolist.Repository.TaskRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class MainController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("taskList", taskRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id) {
        taskRepository.deleteById(id);
        return "redirect:/";    
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("title", taskRepository.findById(id).get().getTitle());
        model.addAttribute("description", taskRepository.findById(id).get().getDescription());
        return "edit";    
    }

    @PostMapping("/edit/{id}")
    public String edit(Model model, @ModelAttribute Task task, @PathVariable("id") int id) {
        task.setLastUpdate(new Date());
        taskRepository.save(task);
        return "redirect:/";    
    }

    @GetMapping("/mark/{id}")
    public String mark(Model model, @PathVariable("id") int id) {
        Task task = taskRepository.findById(id).get();
        task.setStatus("Completed");
        task.setLastUpdate(new Date());
        taskRepository.save(task);
        return "redirect:/";  
    }

    @PostMapping("/")
    public String add(Model model, @ModelAttribute Task task) throws Exception {
        task.setLastUpdate(new Date());
        task.setStatus("Not completed");
        taskRepository.save(task);
        return "redirect:/";  
    }
}
