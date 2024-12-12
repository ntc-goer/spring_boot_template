package personal.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class TodoDto {
    private int id;
    @Size(min = 5, max = 20, message = "Title must be greater than 2")
    private String title;
    private String userName;

    @NotEmpty
    @NotNull
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime targetDate;
    private boolean completed;

    public TodoDto(LocalDateTime targetDate, int id, String title,String userName, String content, boolean completed) {
        this.targetDate = targetDate;
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.completed = completed;
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(min = 5, max = 20, message = "Title must be greater than 2") String getTitle() {
        return title;
    }

    public void setTitle(@Size(min = 5, max = 20, message = "Title must be greater than 2") String title) {
        this.title = title;
    }

    public @NotEmpty @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotEmpty @NotNull String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public TodoDto() {}
}
