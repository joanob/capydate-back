package com.capyjella.capydate.user.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User getUser(User user) {
        return user;
    }
}
