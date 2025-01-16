package com.joanob.yourownboss;

import com.joanob.yourownboss.user.role.Role;
import com.joanob.yourownboss.user.role.RoleRepository;
import com.joanob.yourownboss.user.user.User;
import com.joanob.yourownboss.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

// Todas las fechas se tienen que almacenar en la base de datos como tipo Long, indicando el número de milisegundos epoch. La clase Instant permite gestionar las fechas y horas en Java

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class YourOwmBossApplication {

    @Value("${application.security.admin.username}")
    private String adminUsername;

    @Value("${application.security.admin.password}")
    private String adminPassword;

    public static void main(String[] args) {
        SpringApplication.run(YourOwmBossApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Carga los roles en la base de datos
            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder().name("USER").build());
            }
            if (roleRepository.findByName("ADMIN").isEmpty()) {
                roleRepository.save(Role.builder().name("ADMIN").build());
            }

            // Create user admin with default password admin
            User adminUser = userRepository.findByUsername(adminUsername).orElse(null);
            if (adminUser == null) {
                createAdminUser(roleRepository, userRepository, passwordEncoder);
            }
        };
    }

    private void createAdminUser(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        Role userRole = roleRepository.findByName("USER").orElseThrow(() -> new IllegalStateException("ROLE USER not initialized"));
        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new IllegalStateException("ROLE USER not initialized"));
        User user = User.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .accountLocked(false)
                .enabled(true)
                .roles(List.of(userRole, adminRole))
                .build();
        userRepository.save(user);
    }
}
