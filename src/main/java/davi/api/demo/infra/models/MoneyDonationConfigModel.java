package davi.api.demo.infra.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "money_donation_configs")
@Getter
@Setter
public class MoneyDonationConfigModel extends Model {
    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private long goal;

    @Column(length = 500)
    private String useDescription;

    @OneToOne
    @JoinColumn(name = "campaign_id")
    private CampaignModel campaign;

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
