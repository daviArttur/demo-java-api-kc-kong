package davi.api.demo.domain.builders;

import davi.api.demo.domain.dtos.DomainCampaignBasicDataDto;
import davi.api.demo.domain.dtos.DomainCreateItemDonationConfigDto;
import davi.api.demo.domain.dtos.DomainCreateMoneyDonationConfigDto;
import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.domain.entities.Item;
import davi.api.demo.domain.entities.ItemDonationConfig;
import davi.api.demo.domain.entities.MoneyDonationConfig;

import java.util.ArrayList;

public class CampaignBuilder {
    private Campaign campaign;

    public void withBasicCampaignData(DomainCampaignBasicDataDto dto) {
        campaign.setDescription(dto.description());
        campaign.setTitle(dto.title());
    }

    public void withMoneyDonationConfig(DomainCreateMoneyDonationConfigDto dto) {
        var config = new MoneyDonationConfig(null, null, dto.enabled());
        config.setGoal(dto.goal());
        config.setUseDescription(dto.useDescription());
        campaign.setMoneyDonationConfig(config);
    }

    public void withItemDonationConfig(DomainCreateItemDonationConfigDto createItemDonationConfigDto) {
        var config = new ItemDonationConfig(null, null, createItemDonationConfigDto.enabled());
        var items = new ArrayList<Item>();
        for (var dto : createItemDonationConfigDto.createItemsDto()) {
            var item = new Item(null, null, dto.name(), dto.quantityReceived(), dto.quantityGoal());
            items.add(item);
        }
        config.setItems(items);
        campaign.setItemDonationConfig(config);
    }

    public Campaign build() {
        return campaign;
    }
}
