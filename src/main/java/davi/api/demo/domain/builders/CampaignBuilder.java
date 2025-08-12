package davi.api.demo.domain.builders;

import davi.api.demo.domain.dtos.DomainCampaignBasicDataDto;
import davi.api.demo.domain.dtos.DomainCreateItemDonationConfigDto;
import davi.api.demo.domain.dtos.DomainCreateMoneyDonationConfigDto;
import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.domain.entities.Item;
import davi.api.demo.domain.entities.ItemDonationConfig;
import davi.api.demo.domain.entities.MoneyDonationConfig;

import java.util.ArrayList;
import java.util.UUID;

public class CampaignBuilder {
    private final Campaign campaign = new Campaign();

    public CampaignBuilder withBasicCampaignData(DomainCampaignBasicDataDto dto) {
        campaign.setDescription(dto.description());
        campaign.setTitle(dto.title());
        return this;
    }

    public CampaignBuilder withMoneyDonationConfig(DomainCreateMoneyDonationConfigDto dto) {
        var config = new MoneyDonationConfig(null, UUID.randomUUID().toString(), dto.enabled());
        config.setGoal(dto.goal());
        config.setUseDescription(dto.useDescription());
        campaign.setMoneyDonationConfig(config);
        return this;
    }

    public CampaignBuilder withItemDonationConfig(DomainCreateItemDonationConfigDto createItemDonationConfigDto) {
        var config = new ItemDonationConfig(null, UUID.randomUUID().toString(), createItemDonationConfigDto.enabled());
        var items = new ArrayList<Item>();
        for (var dto : createItemDonationConfigDto.createItemsDto()) {
            var item = new Item(null, UUID.randomUUID().toString(), dto.name(), dto.quantityGoal(), dto.quantityReceived());
            items.add(item);
        }
        config.setItems(items);
        campaign.setItemDonationConfig(config);
        return this;
    }

    public Campaign build() {
        return campaign;
    }
}
