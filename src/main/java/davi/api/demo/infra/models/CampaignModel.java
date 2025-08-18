package davi.api.demo.infra.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
public class CampaignModel extends Model {
    private String title;
    private String description;

    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
    private MoneyDonationConfigModel moneyDonationConfig;

    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
    private ItemDonationConfigModel itemDonationConfig;
}
