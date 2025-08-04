package davi.api.demo.infra.repositories.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
public class CampaignModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;
    private String title;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "money_donation_config_id")
    private MoneyDonationConfigModel moneyDonationConfig;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_donation_config_id")
    private ItemDonationConfigModel itemDonationConfig;
}
