package com.joanob.yourownboss.admin.resources;

import com.joanob.yourownboss.admin.resources.dto.AdminResourceRequest;
import com.joanob.yourownboss.resources.Resource;
import com.joanob.yourownboss.resources.ResourceRepository;
import com.joanob.yourownboss.user.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminResourceService {

    private final ResourceRepository repository;

    public void createResource(@Valid AdminResourceRequest request, User user) {
        Resource resource = Resource.builder()
                .id(request.id())
                .name(request.name())
                .build();
        repository.save(resource);
    }

    public void modifyResource(@Valid AdminResourceRequest request, User user) {
        Resource resource = repository.findById(request.id()).orElseThrow(() -> new EntityNotFoundException("Resource " + request.id() + " not found"));

        resource.setName(request.name());
        repository.save(resource);
    }

    public void deleteResource(String resourceId, User user) {
        Resource resource = repository.findById(resourceId).orElseThrow(() -> new EntityNotFoundException("Resource " + resourceId + " not found"));
        repository.delete(resource);
    }
}
