package com.capyjella.capydate.user.auth;


import com.capyjella.capydate.user.auth.dto.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> signup(
            @RequestBody @Valid SignupRequest request
    ) {
        service.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
