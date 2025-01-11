package com.capyjella.capydate.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private boolean deleted;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Long createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private Long lastModifiedDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Integer createBy;
    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;
}