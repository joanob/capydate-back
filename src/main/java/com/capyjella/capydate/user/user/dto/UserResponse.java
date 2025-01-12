package com.capyjella.capydate.user.user.dto;

import com.capyjella.capydate.user.role.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private boolean accountLocked;
    private boolean enabled;
    private Long createdDate;
    private Long lastModifiedDate;
    private List<Role> roles;
}
