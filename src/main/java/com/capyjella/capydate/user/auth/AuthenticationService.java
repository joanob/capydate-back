package com.capyjella.capydate.user.auth;

import com.capyjella.capydate.config.security.JwtService;
import com.capyjella.capydate.user.auth.dto.LoginRequest;
import com.capyjella.capydate.user.auth.dto.SignupRequest;
import com.capyjella.capydate.user.role.Role;
import com.capyjella.capydate.user.role.RoleRepository;
import com.capyjella.capydate.user.user.User;
import com.capyjella.capydate.user.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void signup(SignupRequest request) {
        Role userRole = roleRepository.findByName("USER").orElseThrow(() -> new IllegalStateException("ROLE USER not initialized"));
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(true)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
    }

    public String login(@Valid LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        HashMap claims = new HashMap<String, Object>();
        User user = (User) auth.getPrincipal();
        String jwt = jwtService.generateToken(claims, user);
        return jwt.toString();
    }
}
