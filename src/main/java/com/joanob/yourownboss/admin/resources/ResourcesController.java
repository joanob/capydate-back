package com.joanob.yourownboss.admin.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/resources")
@RequiredArgsConstructor
public class ResourcesController {

    @GetMapping
    public ResponseEntity<?> getAll(
            Authentication authentication
    ) {
        return ResponseEntity.ok().build();
    }

}
