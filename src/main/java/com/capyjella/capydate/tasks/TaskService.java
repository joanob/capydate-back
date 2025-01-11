package com.capyjella.capydate.tasks;

import com.capyjella.capydate.common.PageResponse;
import com.capyjella.capydate.common.exceptions.OperationNotPermitedException;
import com.capyjella.capydate.tasks.dto.CreateTaskRequest;
import com.capyjella.capydate.tasks.dto.TaskMapper;
import com.capyjella.capydate.tasks.dto.TaskResponse;
import com.capyjella.capydate.user.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public Integer createTask(@Valid CreateTaskRequest request, User user) {
        if (request.date() == 0 && request.startTime() == 0 && request.endTime() == 0) {
            throw new RuntimeException("");
        }
        Task task = mapper.toTask(request);
        task.setUser(user);
        task.setCompleted(false);
        task = repository.save(task);
        return task.getId();
    }

    public void deleteTask(Integer taskId, User user) {
        Task task = repository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new EntityNotFoundException(""));
        if (task.getUser().getId() != user.getId()) {
            throw new OperationNotPermitedException("");
        }
        task.setDeleted(true);
        repository.save(task);
    }

    public PageResponse<TaskResponse> getAllTasksInPeriod(Instant startTime, Instant endTime, Integer page, Integer size, User user) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = repository.findAllInPeriod(pageable, startTime.toEpochMilli(), endTime.toEpochMilli(), user.getId());
        List<TaskResponse> taskList = tasks.stream().map(mapper::toTaskResponse).toList();
        return new PageResponse<>(
                taskList,
                tasks.getNumber(),
                tasks.getSize(),
                tasks.getTotalElements(),
                tasks.getTotalPages(),
                tasks.isFirst(),
                tasks.isLast()
        );
    }

    public void modifyTask(Integer taskId, @Valid CreateTaskRequest request, User user) {
        Task task = repository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new EntityNotFoundException(""));
        if (task.getUser().getId() != user.getId()) {
            throw new OperationNotPermitedException("");
        }
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDate(request.date());
        task.setStartTime(request.startTime());
        task.setEndTime(request.endTime());
        task.setColor(request.color());
        repository.save(task);
    }

    public void setCompletedTask(Integer taskId, boolean completed, User user) {
        Task task = repository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new EntityNotFoundException(""));
        if (task.getUser().getId() != user.getId()) {
            throw new OperationNotPermitedException("");
        }
        task.setCompleted(completed);
        repository.save(task);
    }
}
