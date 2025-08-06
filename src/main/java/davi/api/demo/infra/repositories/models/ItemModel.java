package davi.api.demo.infra.repositories.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "items")
public class ItemModel extends Model {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int quantityGoal;

    @Column(nullable = false)
    private int quantityReceived;

    @ManyToOne
    @JoinColumn(name = "donation_config_id", nullable = false)
    private ItemDonationConfigModel donationConfig;

    public ItemModel() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }

    public ItemModel(Long id, String uuid, String name, int quantityGoal, int quantityReceived, ItemDonationConfigModel donationConfig) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.quantityGoal = quantityGoal;
        this.quantityReceived = quantityReceived;
        this.donationConfig = donationConfig;
    }
}
