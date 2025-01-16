package com.joanob.yourownboss.user.user;

import com.joanob.yourownboss.user.user.dto.UserMapper;
import com.joanob.yourownboss.user.user.dto.UserResponse;
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
