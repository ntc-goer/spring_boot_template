package personal.todo.service;

import personal.todo.dto.TodoDto;
import personal.todo.exception.TodoNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {
    List<TodoDto> findAll();

    TodoDto findById(int id);

    TodoDto create(String title, String userName, String content, LocalDateTime targetDate, boolean completed);

    void deleteById(int id);

    void updateTodo(TodoDto todoDto) throws TodoNotFoundException;
}
