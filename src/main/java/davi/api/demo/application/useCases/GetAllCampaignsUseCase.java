package davi.api.demo.application.useCases;

import davi.api.demo.application.dtos.ApplicationCampaignDto;
import davi.api.demo.application.dtos.ApplicationMoneyDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationItemDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationItemDto;
import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.domain.entities.MoneyDonationConfig;
import davi.api.demo.domain.entities.ItemDonationConfig;
import davi.api.demo.domain.entities.Item;
import davi.api.demo.domain.repositories.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllCampaignsUseCase {
    private final CampaignRepository campaignRepository;

    public GetAllCampaignsUseCase(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<ApplicationCampaignDto> perform() {
        try {
            List<Campaign> campaigns = campaignRepository.findAll();
            return campaigns.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private ApplicationCampaignDto convertToDto(Campaign campaign) {
        ApplicationMoneyDonationConfigDto moneyConfig = null;
        ApplicationItemDonationConfigDto itemConfig = null;

        if (campaign.getMoneyDonationConfig() != null) {
            MoneyDonationConfig moneyDonationConfig = campaign.getMoneyDonationConfig();
            moneyConfig = new ApplicationMoneyDonationConfigDto(
                    moneyDonationConfig.isEnabled(),
                    moneyDonationConfig.getGoal(),
                    moneyDonationConfig.getUseDescription()
            );
        }

        if (campaign.getItemDonationConfig() != null) {
            ItemDonationConfig itemDonationConfig = campaign.getItemDonationConfig();
            List<ApplicationItemDto> items = itemDonationConfig.getItems().stream()
                    .map(item -> new ApplicationItemDto(
                            item.getName(),
                            item.getQuantityGoal(),
                            item.getQuantityReceived()
                    ))
                    .collect(Collectors.toList());

            itemConfig = new ApplicationItemDonationConfigDto(
                    itemDonationConfig.isEnabled(),
                    items
            );
        }

        return new ApplicationCampaignDto(
                campaign.getId(),
                campaign.getUuid(),
                campaign.getTitle(),
                campaign.getDescription(),
                moneyConfig,
                itemConfig
        );
    }
} 