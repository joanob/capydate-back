package com.joanob.yourownboss.admin.masschanging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joanob.yourownboss.resources.Resource;
import com.joanob.yourownboss.resources.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

// https://stackoverflow.com/questions/44261069/how-to-download-response-as-a-json-file-created-from-java-object
@RestController
@RequestMapping("admin/mass")
@RequiredArgsConstructor
public class AdminMassChangingController {
    private final ResourceRepository resourceRepository;

    @GetMapping()
    public ResponseEntity<?> downloadJSONWithAllData(
            Authentication authentication
    ) throws JsonProcessingException {
        MassChangingDTO dto = MassChangingDTO.builder().build();

        List<Resource> resources = resourceRepository.findAll();
        dto.setResources(resources);

        byte[] jsonBytes = new ObjectMapper().writeValueAsBytes(dto);

        String filename = "YOB-" + Instant.now().toString();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentLength(jsonBytes.length)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBytes);

    }
}
