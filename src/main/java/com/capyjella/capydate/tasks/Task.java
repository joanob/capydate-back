package com.capyjella.capydate.tasks;

import com.capyjella.capydate.common.BaseEntity;
import com.capyjella.capydate.user.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task extends BaseEntity {
    private String title;
    private String description;
    private boolean completed;
    private Long date;
    private Long startTime;
    private Long endTime;
    private String color;

    @ManyToOne
    private User user;
}
