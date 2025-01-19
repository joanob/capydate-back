package com.joanob.yourownboss.admin.masschanging;

import com.joanob.yourownboss.resources.Resource;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MassChangingDTO {
    private List<Resource> resources;
}
