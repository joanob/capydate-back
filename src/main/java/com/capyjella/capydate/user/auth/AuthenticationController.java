package com.capyjella.capydate.user.auth;


import com.capyjella.capydate.user.auth.dto.LoginRequest;
import com.capyjella.capydate.user.auth.dto.LoginResponse;
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest login
    ) {
        String jwt = service.login(login);
        var response = LoginResponse.builder().token(jwt).build();
        return ResponseEntity.ok(response);
    }
}
