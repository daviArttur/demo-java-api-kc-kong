package davi.api.demo.infra.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel extends Model {
    public String email;

    // Getters and setters
}

