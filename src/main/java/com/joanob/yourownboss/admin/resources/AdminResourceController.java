package com.joanob.yourownboss.admin.resources;

import com.joanob.yourownboss.admin.resources.dto.AdminResourceRequest;
import com.joanob.yourownboss.user.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/resources")
@RequiredArgsConstructor
public class AdminResourceController {

    private final AdminResourceService service;

    @PostMapping
    public ResponseEntity<Integer> createTask(
            @RequestBody @Valid AdminResourceRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.createResource(request, (User) authentication.getPrincipal()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Integer> modifyResource(
            @PathVariable("id") Integer resourceId,
            @RequestBody @Valid AdminResourceRequest request,
            Authentication authentication
    ) {
        service.modifyResource(resourceId, request, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> modifyResource(
            @PathVariable("id") Integer resourceId,
            Authentication authentication
    ) {
        service.deleteResource(resourceId, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }
}
