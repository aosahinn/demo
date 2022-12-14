package com.preschool.demo.data.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
//@EntityListeners({AuditingEntityListener.class})
@Data
public abstract class BaseEntity extends IdEntity {
    @Version
    @Column(
            name = "row_version"
    )
    protected long rowVersion;
    @CreatedDate
    @Column(
            name = "created_date",
            nullable = false
    )
    protected ZonedDateTime createdDate = ZonedDateTime.now();
    @LastModifiedDate
    @Column(
            name = "last_modified_date"
    )
    protected ZonedDateTime lastModifiedDate = ZonedDateTime.now();
    @CreatedBy
    @Column(
            name = "created_by",
            updatable = false,
            length = 50
    )
    protected String createdBy;
    @LastModifiedBy
    @Column(
            name = "last_modified_by",
            length = 50
    )
    protected String lastModifiedBy;

    protected BaseEntity() {
    }
}
