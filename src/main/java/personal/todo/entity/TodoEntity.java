package personal.todo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "userName")
    private String userName;
    @Column(name = "content")
    private String content;
    @Column(name = "targetDate")
    private LocalDateTime targetDate;
    @Column(name = "completed")
    private boolean completed;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TodoEntity(int id, String title, String userName, String content, LocalDateTime targetDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.targetDate = targetDate;
        this.completed = completed;
    }

    public TodoEntity(String title, String userName, String content, LocalDateTime targetDate, boolean completed) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.targetDate = targetDate;
        this.completed = completed;
    }

    public TodoEntity(){}



}
