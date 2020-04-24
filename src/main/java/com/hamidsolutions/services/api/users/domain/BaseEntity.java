package com.hamidsolutions.services.api.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Massaoud
 */


@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    // @Column(nullable = false,name = "created_by" ,length = 50, updatable = false)
    @CreatedBy
    private String createdBy;

    //@Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @CreatedDate
    // @Column(name = "created_date", nullable = false,updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    //@Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    // @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();

    private boolean dataEnable = true;
}
