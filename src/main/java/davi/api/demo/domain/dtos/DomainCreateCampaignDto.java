package davi.api.demo.domain.dtos;

import davi.api.demo.domain.entities.ItemDonationConfig;
import davi.api.demo.domain.entities.MoneyDonationConfig;

public record DomainCreateCampaignDto(
        Long id,
        String uuid,
        String title,
        String description,
        MoneyDonationConfig moneyDonationConfig,
        ItemDonationConfig itemDonationConfig
) { }
