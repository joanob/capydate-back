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

    public Integer createResource(@Valid AdminResourceRequest request, User user) {
        Resource resource = Resource.builder()
                .name(request.name())
                .build();
        resource = repository.save(resource);
        return resource.getId();
    }

    public void modifyResource(Integer resourceId, @Valid AdminResourceRequest request, User user) {
        Resource resource = repository.findById(resourceId).orElseThrow(() -> new EntityNotFoundException("Resource " + resourceId + " not found"));
        resource.setName(request.name());
        repository.save(resource);
    }

    public void deleteResource(Integer resourceId, User user) {
        Resource resource = repository.findById(resourceId).orElseThrow(() -> new EntityNotFoundException("Resource " + resourceId + " not found"));
        repository.delete(resource);
    }
}
