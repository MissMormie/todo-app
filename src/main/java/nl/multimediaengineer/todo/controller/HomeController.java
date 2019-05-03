/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.multimediaengineer.todo.controller;

import java.util.Date;
import javax.validation.Valid;
import nl.multimediaengineer.todo.model.Todo;
import nl.multimediaengineer.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controls the homepage
 * @author sonja
 */
@Controller
public class HomeController {
    private final TodoRepository todoRepository;

    // ------------------ CONSTRUCTOR --------------------------------------------
    @Autowired
    public HomeController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    /**
     * Adds a list of all todo's to the model.
     * @param model
     * @return String for viewResolver
     */
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        Iterable<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos", todos);

        Todo todo = new Todo();
        todo.setEndDate(new Date());
        model.addAttribute("todo", todo);
        
        return "home";
    }
    
    
    /**
     * Saves the posted todo, adds a list of all todo's to the model.
     * @param model
     * @return String for viewResolver
     */
    @PostMapping(value = "/home")
    public String home(@ModelAttribute @Valid Todo todo, Errors errors, Model model) {
        if(errors.hasErrors()) {
           model.addAttribute("todo", todo);
        } else {
            Todo todoObject = new Todo();
            todoObject.setEndDate(new Date());
            model.addAttribute("todo", todoObject);
            todoRepository.save(todo);
        }

        Iterable<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos", todos);
        
        return "home";
    }
    
    
    
    
}
