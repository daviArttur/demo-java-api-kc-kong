package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDonation extends Entity {

    public ItemDonation(Long id, String uuid) {
        super(id, uuid);
    }
}

