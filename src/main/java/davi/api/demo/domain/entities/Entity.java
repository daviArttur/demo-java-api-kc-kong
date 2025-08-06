package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Entity {
    private final Long id;
    private String uuid;

    public Entity(Long id, String uuid) {
        if (uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
        this.uuid = uuid;
        this.id = id;
    }
}
