package personal.todo.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import personal.todo.dto.TodoDto;
import personal.todo.service.TodoService;

@Controller
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String Home(){
        return "redirect:todo/list";
    }

    @GetMapping("todo/list")
    public String getAll(Model model) {
        var list = todoService.findAll();
        model.addAttribute("list", list);
        return "todo/list";
    }

    @GetMapping("todo/create")
    public String getCreateTodo(Model model) {
        var todo = new TodoDto();
        model.addAttribute("todo", todo);
        return "todo/create";
    }

    @PostMapping("todo/create")
    public String createTodo(
            @ModelAttribute("todo") TodoDto todoDto,
            @SessionAttribute(name = "userName", required = false) String userName,
            BindingResult bindingResult
        ) {
        logger.info("TodoController create todo {} for {}", todoDto, userName);
        if (bindingResult.hasErrors()) {
            return "todo/create";
        }
        this.todoService.create(
                todoDto.getTitle(),
                userName,
                todoDto.getContent(),
                todoDto.getTargetDate(),
                todoDto.isCompleted()
        );
        return "redirect:/todo/list";
    }

    @GetMapping("todo/delete/{id}")
    public String deleteTodo(@PathVariable("id") int id) {
        this.todoService.deleteById(id);
        return "redirect:/todo/list";
    }

    @GetMapping("todo/edit/{id}")
    public String getEditTodo(@PathVariable("id") int id, Model model) {
        var todo = this.todoService.findById(id);
        logger.info("get edit todo of {}", todo);
        model.addAttribute("todo", todo);
        return "todo/edit";
    }

    @PostMapping("todo/edit")
    public String editTodo(
            @Valid @ModelAttribute("todo") TodoDto todoDto,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/todo/edit/%s",todoDto.getId());
        }
        todoService.updateTodo(todoDto);
        return "redirect:/todo/list";
    }
}
