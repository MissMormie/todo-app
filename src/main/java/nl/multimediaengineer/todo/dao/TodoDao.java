/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.multimediaengineer.todo.dao;

import java.util.List;
import nl.multimediaengineer.todo.model.Todo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author sonja
 */
public class TodoDao {
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void save(Todo todo) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(todo);
        tx.commit();
        session.close();
    }
    
    public void delete(long id) {
        
    }
    
    public void delete(Todo todo) {
        
    }
    
    // Todo look at this list?
    public List<Todo> list() {
        List<Todo> todoList;
        try (Session session = this.sessionFactory.openSession()) {
            todoList = session.createQuery("from Todo").list();
        }
        return todoList;
    }
    
    public void update(Todo todo){
        save(todo);
    }
    
    public Todo getTodo(long id){
        return null;
    }
    
}
