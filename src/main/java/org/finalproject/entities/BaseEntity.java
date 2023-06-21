package org.finalproject.entities;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;


@MappedSuperclass
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity extends Auditable<User> implements Serializable {
    @Id

    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "entity_id", nullable = false)
    private Long id;



}














/*import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;
import static jakarta.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract  class BaseEntity<T> {
    @Id
    @Column(name =  "entity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id ;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "created_date")
    protected Date createdDate;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "updated_date")
    protected Date lupdatedDate;

    @CreatedBy
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    private User updatedBy;



}*/
