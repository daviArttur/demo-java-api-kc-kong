package davi.api.demo.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class ItemDonationConfig extends DonationConfig {
    private List<Item> items = new ArrayList<>();

    public ItemDonationConfig(Long id, String uuid, boolean enabled) {
        super(id, uuid, enabled);
    }

    public List<Item> getItems() { return this.items; }

    public void setItems(List<Item> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Item list must not be null or empty.");
        }
        this.items = new ArrayList<>(items); // Cópia defensiva
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

