package com.capyjella.capydate.user.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {
    @NotEmpty(message = "Username is empty")
    @NotBlank(message = "Username is empty")
    private String username;
    @NotEmpty(message = "Password is empty")
    @NotBlank(message = "Password is empty")
    private String password;
}
