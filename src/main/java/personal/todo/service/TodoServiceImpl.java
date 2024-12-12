package personal.todo.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import personal.todo.dto.TodoDto;
import personal.todo.entity.TodoEntity;
import personal.todo.exception.TodoNotFoundException;
import personal.todo.repository.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TodoDto> findAll() {
        List<TodoEntity> todos = todoRepository.findAll();
        return this.modelMapper.map(todos, new TypeToken<List<TodoDto>>() {}.getType());
    }

    @Override
    public TodoDto findById(int id) {
        TodoEntity todo = todoRepository.findById(id).stream().findFirst().orElse(null);
        return this.modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto create(String title, String userName, String content, LocalDateTime targetDate, boolean completed) {
        TodoEntity todo = new TodoEntity(title,userName, content, targetDate, completed);
        todoRepository.save(todo);
        return this.modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public void deleteById(int id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void updateTodo(TodoDto todoDto) throws TodoNotFoundException {
        var item = todoRepository.findById(todoDto.getId()).orElseThrow(
                () -> new TodoNotFoundException("Todo not found with id: " + todoDto.getId()));
        item.setTitle(todoDto.getTitle());
        item.setContent(todoDto.getContent());
        item.setCompleted(todoDto.isCompleted());
        item.setTargetDate(todoDto.getTargetDate());
        todoRepository.save(item);
    }
}
