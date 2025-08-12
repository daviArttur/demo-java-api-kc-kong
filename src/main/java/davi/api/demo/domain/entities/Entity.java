package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Getter
@Setter
public abstract class Entity {
    private final Long id;
    private String uuid;

    public Entity(Long id, String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = uuid;
        }

        this.id = id;
    }
}
