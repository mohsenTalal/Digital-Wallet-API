package com.mohsen.walletapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/*
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
*/

@MappedSuperclass
@EntityListeners(Base.class)
public abstract class Base implements Serializable {

    private static final long  serialVersionUID = 1L;
    /*@Id
    private String id;*/
    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private Date createdDate = Date.from(Instant.now());

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Date lastModifiedDate = Date.from(Instant.now());
 /*   Base(){
        this.createdDate= new Date();
        this.lastModifiedDate = new Date();
    }*/

  /*  @PrePersist
    public void setCreatedAt() {
        createdDate = new Date();
    }
    @PreUpdate
    public void setUpdatedAt() {
        lastModifiedDate = new Date();
    }
    */

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
