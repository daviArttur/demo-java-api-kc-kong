package davi.api.demo.infra.repositories.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String uuid;
    public String email;

    // Getters and setters
}

