package davi.api.demo.domain.entities;

public class User extends Entity {
    public final String email;

    public User(Long id, String uuid, String email) {
        super(id, uuid);
        this.email = email;
    }

    // Getters and business rules
}
