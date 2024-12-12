package personal.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.todo.entity.TodoEntity;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
}
