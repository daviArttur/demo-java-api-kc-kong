package davi.api.demo.domain.entities;

import java.util.UUID;

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

    public Long getId() { return id; }

    public String getUuid() { return uuid; }
}
