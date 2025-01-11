package com.capyjella.capydate.tasks.dto;

import com.capyjella.capydate.common.BaseEntityResponse;
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
public class TaskResponse extends BaseEntityResponse {
    private String title;
    private String description;
    private boolean completed;
    private long date;
    private long startTime;
    private long endTime;
}
