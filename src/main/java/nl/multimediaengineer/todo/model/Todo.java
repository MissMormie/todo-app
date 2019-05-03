/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.multimediaengineer.todo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.lang.NonNull;

/**
 *
 * @author sonja
 */
@Entity
@Table(name = "TODO")
public class Todo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long Id;
    
    @NonNull
    private String title;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private Priority priority;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
            
    
    
}
