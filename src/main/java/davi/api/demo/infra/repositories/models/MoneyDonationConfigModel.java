package davi.api.demo.infra.repositories.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "money_donation_configs")
@Getter
@Setter
public class MoneyDonationConfigModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private long goal;

    @Column(length = 500)
    private String useDescription;

    public MoneyDonationConfigModel() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }

    public MoneyDonationConfigModel(Long id, String uuid, boolean enabled, long goalInCents, String useDescription) {
        this.id = id;
        this.uuid = uuid;
        this.enabled = enabled;
        this.goal = goalInCents;
        this.useDescription = useDescription;
    }
}
