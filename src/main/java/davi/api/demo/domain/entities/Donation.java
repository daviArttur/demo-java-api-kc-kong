package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Donation extends Entity {

    public Donation(Long id, String uuid) {
        super(id, uuid);
    }
}
