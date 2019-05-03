/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.multimediaengineer.todo.repository;

import java.io.Serializable;
import nl.multimediaengineer.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sonja
 */
public interface TodoRepository extends CrudRepository<Todo, Long> { 
}
