package com.capyjella.capydate.dev;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("populate")
@RequiredArgsConstructor
public class PopulateDatabase {

    private final Environment environment;
    private final AsyncPopulateService service;

    @PostMapping
    public ResponseEntity<?> populateDatabase() {
        if (environment.getActiveProfiles().length == 1 && environment.getActiveProfiles()[0].contentEquals("dev")) {
            service.populateDb();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
