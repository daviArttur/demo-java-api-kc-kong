package davi.api.demo.infra.repositories.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true, updatable = false)
    protected String uuid;

    @Column(nullable = false, unique = true, updatable = false)
    protected Date createdAt;

    @Column(nullable = false)
    protected Date updatedAt;

    @Column()
    protected Date deletedAt;
}
