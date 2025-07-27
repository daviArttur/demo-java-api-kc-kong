package davi.api.demo.domain.entities;

import davi.api.demo.domain.dtos.DomainCreateCampaignDto;

public class Campaign extends Entity {
    private String title;
    private String description;
    private MoneyDonationConfig moneyDonationConfig;
    private ItemDonationConfig itemDonationConfig;

    public Campaign () { super(null, null); };

    public Campaign (DomainCreateCampaignDto dto) throws Exception {
        super(dto.id(), dto.uuid());
        this.title = dto.title();
        this.description = dto.description();
        this.moneyDonationConfig = dto.moneyDonationConfig();
        this.itemDonationConfig = dto.itemDonationConfig();
        validate();
    };

    public void setMoneyDonationConfig(MoneyDonationConfig config) {
        moneyDonationConfig = config;
    }

    public void setItemDonationConfig(ItemDonationConfig config) {
        itemDonationConfig = config;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void validate() throws Exception {
        if (title == null || title.length() < 4 || title.length() > 40) {
            throw new IllegalArgumentException("Title must have between 4 and 40 characters.");
        }
        if (description == null || description.length() < 4 || description.length() > 999) {
            throw new IllegalArgumentException("Description must have between 4 and 999 characters.");
        }
    }
}




