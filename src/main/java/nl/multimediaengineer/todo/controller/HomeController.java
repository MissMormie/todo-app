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
 3
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
    
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        Iterable<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos", todos);

        Todo todo = new Todo();
        todo.setEndDate(new Date());
        model.addAttribute("todo", todo);
        
        return "home";
    }
    
    @PostMapping(value = "/home")
    public String home(@ModelAttribute @Valid Todo todo, Errors errors, Model model) {
        Iterable<Todo> todos = todoRepository.findAll();
        
        model.addAttribute("todos", todos);

        
        if(errors.hasErrors()) {
           model.addAttribute("todo", todo);
        } else {
            model.addAttribute("todo", new Todo());
            todoRepository.save(todo);
        }
        
        return "home";
        
        // TODO set up redirect so refreshing does not resend data.
//        return "home";
    }
    
    
    
    
}
