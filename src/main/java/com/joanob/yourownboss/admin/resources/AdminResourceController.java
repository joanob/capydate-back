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
    public ResponseEntity<?> createResource(
            @RequestBody @Valid AdminResourceRequest request,
            Authentication authentication
    ) {
        service.createResource(request, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> modifyResource(
            @RequestBody @Valid AdminResourceRequest request,
            Authentication authentication
    ) {
        service.modifyResource(request, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(
            @PathVariable("id") String resourceId,
            Authentication authentication
    ) {
        service.deleteResource(resourceId, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }
}
