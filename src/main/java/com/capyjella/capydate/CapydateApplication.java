package com.capyjella.capydate;

import com.capyjella.capydate.user.role.Role;
import com.capyjella.capydate.user.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

// Todas las fechas se tienen que almacenar en la base de datos como tipo Long, indicando el número de milisegundos epoch. La clase Instant permite gestionar las fechas y horas en Java

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class CapydateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapydateApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository) {
        // carga los roles en la base de datos
        return args -> {
            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder().name("USER").build());
            }
        };
    }
}
