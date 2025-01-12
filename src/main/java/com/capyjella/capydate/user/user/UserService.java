package com.capyjella.capydate.user.user;

import com.capyjella.capydate.user.user.dto.UserMapper;
import com.capyjella.capydate.user.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponse getUser(User user) {
        return mapper.toUserResponse(user);
    }
}
