package com.joanob.yourownboss.admin.masschanging;

import com.joanob.yourownboss.resources.Resource;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MassChangingDTO {
    public List<Resource> resources;
}
