package io.github.tbarissatir.luv_todo.repository;

import io.github.tbarissatir.luv_todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
