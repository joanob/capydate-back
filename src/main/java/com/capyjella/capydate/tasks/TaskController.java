package com.capyjella.capydate.tasks;

import com.capyjella.capydate.common.PageResponse;
import com.capyjella.capydate.common.exceptions.IncorrectDataException;
import com.capyjella.capydate.tasks.dto.CreateTaskRequest;
import com.capyjella.capydate.tasks.dto.TaskResponse;
import com.capyjella.capydate.user.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<Integer> createTask(
            @RequestBody @Valid CreateTaskRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.createTask(request, (User) authentication.getPrincipal()));
    }

    @GetMapping("/{year}/{month}/{day}")
    public ResponseEntity<PageResponse<TaskResponse>> getAllTasksInDate(
            @PathVariable("year") String year,
            @PathVariable("month") String month,
            @PathVariable("day") String day,
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            Authentication authentication
    ) {
        if (!isDateValid(year, month, day)) {
            throw new IncorrectDataException("Date " + year + "-" + month + "-" + day + " is not valid");
        }
        Instant startTime = Instant.parse(year + "-" + month + "-" + day + "T00:00:00.00Z");
        Instant endTime = startTime.plusSeconds(24 * 3600);
        return ResponseEntity.ok(service.getAllTasksInPeriod(startTime, endTime, page, size, (User) authentication.getPrincipal()));
    }

    @GetMapping
    public ResponseEntity<PageResponse<TaskResponse>> getAllTasksInDate(
            @RequestParam(name = "startTime", required = true) Instant startTime,
            @RequestParam(name = "endTime", required = true) Instant endTime,
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.getAllTasksInPeriod(startTime, endTime, page, size, (User) authentication.getPrincipal()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Integer> modifyTask(
            @PathVariable("id") Integer taskId,
            @RequestBody @Valid CreateTaskRequest request,
            Authentication authentication
    ) {
        service.modifyTask(taskId, request, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Integer> completeTask(
            @PathVariable("id") Integer taskId,
            Authentication authentication
    ) {
        service.setCompletedTask(taskId, true, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/complete/false")
    public ResponseEntity<Integer> completeTaskFalse(
            @PathVariable("id") Integer taskId,
            Authentication authentication
    ) {
        service.setCompletedTask(taskId, false, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable("id") Integer taskId,
            Authentication authentication
    ) {
        service.deleteTask(taskId, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    private boolean isDateValid(String year, String month, String day) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(year + "-" + month + "-" + day);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
