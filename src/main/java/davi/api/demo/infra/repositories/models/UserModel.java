package davi.api.demo.infra.repositories.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel extends Model {
    public String email;

    // Getters and setters
}

