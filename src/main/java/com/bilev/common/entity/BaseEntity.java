package com.bilev.common.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.UUID;

/**
 * Base entity using UUID as primary key type.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    public UUID id;

    @Column(name = "modified_at", nullable = false)
    public Date modifiedAt;

    @Column(name = "modified_by", nullable = false)
    public Date modifiedBy;

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        modifiedAt = new Date(System.currentTimeMillis());
    }

}
