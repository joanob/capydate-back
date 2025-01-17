package com.joanob.yourownboss.resources.dto;

import com.joanob.yourownboss.common.BaseEntityResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse extends BaseEntityResponse {
    private String name;
}
