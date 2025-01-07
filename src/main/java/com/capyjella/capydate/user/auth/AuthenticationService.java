package com.capyjella.capydate.user.auth;

import com.capyjella.capydate.config.security.JwtService;
import com.capyjella.capydate.user.auth.dto.SignupRequest;
import com.capyjella.capydate.user.role.Role;
import com.capyjella.capydate.user.role.RoleRepository;
import com.capyjella.capydate.user.user.User;
import com.capyjella.capydate.user.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
    }
}
