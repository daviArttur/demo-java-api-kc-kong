package davi.api.demo.application.dtos;

public record ApplicationCampaignDto(
        Long id,
        String uuid,
        String title,
        String description,
        ApplicationMoneyDonationConfigDto moneyDonationConfig,
        ApplicationItemDonationConfigDto itemDonationConfig
) {} 