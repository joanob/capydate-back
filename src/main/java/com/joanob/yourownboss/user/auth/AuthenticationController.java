package com.joanob.yourownboss.user.auth;


import com.joanob.yourownboss.user.auth.dto.LoginRequest;
import com.joanob.yourownboss.user.auth.dto.SignupRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Value("${application.security.jwt.accessTokenCookieName}")
    private String accessTokenCookieName;
    @Value("${application.security.jwt.expiration}")
    private int jwtTokenExpiration;

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
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginRequest login,
            HttpServletResponse response
    ) {
        String jwt = service.login(login);
        Cookie jwtCookie = new Cookie(accessTokenCookieName, jwt);
        jwtCookie.setMaxAge(jwtTokenExpiration / 1000); // jwtTokenExpiration is in miliseconds, not seconds
        jwtCookie.setPath("/");
        //jwtCookie.setSecure(true);
        //jwtCookie.setHttpOnly(true);
        response.addCookie(jwtCookie);
        return ResponseEntity.ok().build();
    }
}
