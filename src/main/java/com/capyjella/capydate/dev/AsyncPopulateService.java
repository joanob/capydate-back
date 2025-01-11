package com.capyjella.capydate.dev;

import com.capyjella.capydate.tasks.Task;
import com.capyjella.capydate.tasks.TaskRepository;
import com.capyjella.capydate.user.role.Role;
import com.capyjella.capydate.user.role.RoleRepository;
import com.capyjella.capydate.user.user.User;
import com.capyjella.capydate.user.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AsyncPopulateService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TaskRepository taskRepository;

    private final String characters = "asdfghjklñqwertyuiopzxcvbnm1234567890";

    private final int usersLength = 50;
    private final int tasksLength = 1000;

    private List<User> users = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    @Async
    public void populateDb() {
        populateUsers();
        populateTasks();
        System.out.println("PopulateDB ended");
    }

    private void populateUsers() {
        Role userRole = roleRepository.findByName("USER").orElse(new Role());
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        for (int i = 0; i < usersLength; i++) {
            String username = getRandomString();
            User user = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(username))
                    .accountLocked(Math.random() > 0.9)
                    .enabled(Math.random() < 0.8)
                    .roles(roles)
                    .build();
            try {
                user = userRepository.save(user);
                users.add(user);
            } catch (Exception e) {

            }
        }
    }

    private void populateTasks() {
        for (int i = 0; i < tasksLength; i++) {
            User randomUser = users.get((int) Math.floor(Math.random() * users.size()));
            Task task = Task.builder()
                    .title(getRandomString())
                    .completed(Math.random() > 0.5)
                    .deleted(Math.random() > 0.25)
                    .user(randomUser)
                    .createBy(randomUser.getId())
                    .createdDate(Instant.now().toEpochMilli())
                    .build();
            if (Math.random() > 0.5) {
                task.setDate(getRandomInstant().toEpochMilli());
            } else {
                task.setStartTime(getRandomInstant().toEpochMilli());
                task.setEndTime(getRandomInstant().plusSeconds(getRandomLong(12 * 3600)).toEpochMilli());
            }
            task = taskRepository.save(task);
            tasks.add(task);
        }
    }

    private String getRandomString() {
        Random rand = new Random();
        int length = rand.nextInt(20) + 1;
        String randomString = "";
        for (int i = 0; i < length; i++) {
            randomString += characters.charAt(rand.nextInt(characters.length()));
        }
        return randomString;
    }

    private long getRandomLong(long bound) {
        Random rand = new Random();
        return rand.nextLong(bound);
    }

    /**
     * Get random instant between 30 days ago and 30 days later
     *
     * @return
     */
    private Instant getRandomInstant() {
        Instant now = Instant.now();
        if (Math.random() > 0.5) {
            return now.plusSeconds(getRandomLong(30 * 24 * 3600));
        } else {
            return now.minusSeconds(getRandomLong(30 * 24 * 3600));
        }
    }
}
