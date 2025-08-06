package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item extends Entity {
    private String name;
    private int quantityGoal;
    private int quantityReceived;

    public Item(Long id, String uuid, String name, int quantityGoal, int quantityReceived) {
        super(id, uuid);
        this.name = name;
        this.quantityGoal = quantityGoal;
        this.quantityReceived = quantityReceived;
        validate();
    }

    public void validate() {
        if (name == null || name.isBlank() || name.length() < 2 || name.length() > 100) {
            throw new IllegalArgumentException("Name must be between 2 and 100 characters.");
        }
        if (quantityGoal <= 0) {
            throw new IllegalArgumentException("Quantity goal must be greater than 0.");
        }
        if (quantityReceived < 0) {
            throw new IllegalArgumentException("Quantity received cannot be negative.");
        }
    }
}
