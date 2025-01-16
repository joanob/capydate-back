package com.joanob.yourownboss.user.user;

import com.joanob.yourownboss.user.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.getUser((User) authentication.getPrincipal()));
    }
}
