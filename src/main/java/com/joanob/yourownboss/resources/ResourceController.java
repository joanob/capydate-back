package com.joanob.yourownboss.resources;

import com.joanob.yourownboss.common.PageResponse;
import com.joanob.yourownboss.resources.dto.ResourceResponse;
import com.joanob.yourownboss.user.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService service;

    @GetMapping
    public ResponseEntity<PageResponse<ResourceResponse>> getAllTasksInDate(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.getAllResources(page, size, (User) authentication.getPrincipal()));
    }
}
