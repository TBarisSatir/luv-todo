package io.github.tbarissatir.luv_todo.service;

import io.github.tbarissatir.luv_todo.dto.TaskRequestDto;
import io.github.tbarissatir.luv_todo.dto.TaskResponseDto;
import io.github.tbarissatir.luv_todo.model.Task;
import io.github.tbarissatir.luv_todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.github.tbarissatir.luv_todo.exception.TaskNotFoundException;
import io.github.tbarissatir.luv_todo.client.RuleEngineClient;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final RuleEngineClient ruleEngineClient;
    private final TaskRepository repository;

    public Page<TaskResponseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    private TaskResponseDto map(Task t) {
        return new TaskResponseDto(t.getId(), t.getTitle(), t.getDescription(), t.getStatus());
    }

    public TaskResponseDto update(Long id, TaskRequestDto dto) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        return map(repository.save(task));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public TaskResponseDto getById(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return map(task);
    }
    public TaskResponseDto create(TaskRequestDto dto) {

        // RULE ENGINE CALL
        var ruleResult = ruleEngineClient.check(dto.description());

        if (ruleResult != null && ruleResult.isMatched()) {
            throw new RuleViolationException(ruleResult.getRuleMsg());
        }

        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());

        return map(repository.save(task));
    }


}
