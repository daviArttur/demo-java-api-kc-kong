package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ItemDonationConfig extends DonationConfig {
    private List<Item> items = new ArrayList<>();

    public ItemDonationConfig(Long id, String uuid, boolean enabled) {
        super(id, uuid, enabled);
    }

    public void validate() {
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("You must configure at least one item.");
        }
        for (Item item : items) {
            item.validate(); // Supondo que `Item` tenha um método `validate()`
        }
    }
}

