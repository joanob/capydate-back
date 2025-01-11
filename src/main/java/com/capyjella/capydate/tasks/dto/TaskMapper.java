package com.capyjella.capydate.tasks.dto;

import com.capyjella.capydate.tasks.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {
    public Task toTask(CreateTaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .date(request.date())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .color(request.color())
                .build();
    }

    public TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .date(task.getDate())
                .startTime(task.getStartTime())
                .endTime(task.getEndTime())
                .color(task.getColor())
                .build();
    }
}
