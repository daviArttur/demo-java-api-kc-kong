package davi.api.demo.infra.repositories.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item_donation_configs")
@Getter
@Setter
public class ItemDonationConfigModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "donationConfig", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemModel> items = new ArrayList<>();

    public ItemDonationConfigModel() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }

    public ItemDonationConfigModel(Long id, String uuid, boolean enabled, List<ItemModel> items) {
        this.id = id;
        this.uuid = uuid;
        this.enabled = enabled;
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
    }
}
