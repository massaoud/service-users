package com.hamidsolutions.services.api.users.dto;


import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Massaoud
 */


@Data
public abstract class BaseDTO implements Serializable {
   private String createdBy;
   private String modifiedBy;
    private Instant createdDate = Instant.now();
    private String lastModifiedBy;
    private Instant lastModifiedDate = Instant.now();
    private boolean enable = true;
}
