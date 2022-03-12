package by.bntu.fitr.taskservice.repository;

import by.bntu.fitr.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
