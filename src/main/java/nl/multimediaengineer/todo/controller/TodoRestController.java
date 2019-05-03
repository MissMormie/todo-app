/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.multimediaengineer.todo.controller;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import nl.multimediaengineer.todo.model.Todo;
import nl.multimediaengineer.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sonja
 */
@RestController
public class TodoRestController {
        private final TodoRepository todoRepository;

            // ------------------ CONSTRUCTOR --------------------------------------------
    @Autowired
    public TodoRestController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
        
//    @RequestMapping(value = "/todo", method = RequestMethod.POST)
//    public Todo createTodo(@RequestBody @Valid Todo todo, Errors errors, HttpServletResponse response) throws IOException {
//        if (errors.hasErrors()) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return null;
//        }
//        todo = todoRepository.save(todo);
//        response.setStatus(HttpServletResponse.SC_OK);
//        return todo;
//    }
//    
    
    @RequestMapping(value = "/todo/{todoId}", method= RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Todo updateTodo(@PathVariable long todoId, @RequestBody @Valid Todo todo, Errors errors,  HttpServletResponse response) {
        if (errors.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        
        todo = todoRepository.save(todo);
        response.setStatus(HttpServletResponse.SC_OK);
        return todo;
    }
        
    @RequestMapping(value = "/todo/{todoId}", method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable long todoId, HttpServletResponse response) {
        todoRepository.deleteById(todoId);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    /**
     * Returns 
     * @param todoId
     * @param response
     * @return the todo item with the passed in Id value. Will respond with 
     * statuscode 200 for existing todos, 204 for non-existing todos.
     */
    @RequestMapping(value = "/todo/{todoId}", method = RequestMethod.GET )
    public Todo readTodo(@PathVariable long todoId, HttpServletResponse response) {
        Optional<Todo> optTodo = todoRepository.findById(todoId);
        Iterable<Todo> findAll = todoRepository.findAll();
        if(optTodo.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
           return optTodo.get();
        }

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return null;
    }
    
    

}
