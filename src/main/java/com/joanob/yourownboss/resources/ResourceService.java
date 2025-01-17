package com.joanob.yourownboss.resources;

import com.joanob.yourownboss.common.PageResponse;
import com.joanob.yourownboss.resources.dto.ResourceMapper;
import com.joanob.yourownboss.resources.dto.ResourceResponse;
import com.joanob.yourownboss.user.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;

    public PageResponse<ResourceResponse> getAllResources(Integer page, Integer size, User user) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Resource> resources = repository.findAll(pageable);
        List<ResourceResponse> resourceResponse = resources.stream().map(mapper::toResourceResponse).toList();
        return new PageResponse<>(
                resourceResponse,
                resources.getNumber(),
                resources.getSize(),
                resources.getTotalElements(),
                resources.getTotalPages(),
                resources.isFirst(),
                resources.isLast()
        );
    }
}
