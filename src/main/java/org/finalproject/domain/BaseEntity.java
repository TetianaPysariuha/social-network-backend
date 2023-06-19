package org.finalproject.domain;

import org.finalproject.domain.hr.User;
import jakarta.persistence.*;
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
    @Column(name = "creation_date")
    protected Date creationDate;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "last_modified_date")
    protected Date lastModifiedDate;

    @CreatedBy
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    private User updatedBy;



}
