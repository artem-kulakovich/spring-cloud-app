package by.bntu.fitr.taskservice.repository;

import by.bntu.fitr.taskservice.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
