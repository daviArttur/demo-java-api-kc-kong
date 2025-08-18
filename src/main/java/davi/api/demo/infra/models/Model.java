package davi.api.demo.infra.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true, updatable = false)
    protected String uuid;

    @Column(nullable = false, updatable = false)
    protected Date createdAt;

    @Column(nullable = false)
    protected Date updatedAt;

    @Column(nullable = true)
    protected Date deletedAt;
}
