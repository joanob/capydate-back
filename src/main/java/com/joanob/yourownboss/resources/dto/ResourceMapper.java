package com.joanob.yourownboss.resources.dto;

import com.joanob.yourownboss.resources.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceMapper {
    public ResourceResponse toResourceResponse(Resource resource) {
        return ResourceResponse.builder()
                .id(resource.getId())
                .name(resource.getName())
                .build();
    }
}
