package org.finalproject.entity;

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
    @Column(name = "id", nullable = false)
    private Long id;

}













