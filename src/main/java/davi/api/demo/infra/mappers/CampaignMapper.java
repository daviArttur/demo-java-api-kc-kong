package davi.api.demo.infra.mappers;

import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.infra.models.CampaignModel;

import java.util.UUID;

public class CampaignMapper {

    public static CampaignModel toEntity(Campaign campaign) {
        if (campaign == null) return null;

        CampaignModel entity = new CampaignModel();
//        entity.setId(campaign.getId());
//        entity.setUuid(campaign.getUuid());
        entity.setTitle(campaign.getTitle());
        entity.setDescription(campaign.getDescription());
        entity.setMoneyDonationConfig(MoneyDonationConfigMapper.toEntity(campaign.getMoneyDonationConfig()));
        entity.setItemDonationConfig(ItemDonationConfigMapper.toEntity(campaign.getItemDonationConfig()));
        entity.setUuid(UUID.randomUUID().toString());
        return entity;
    }

    public static Campaign toDomain(CampaignModel campaignModel) {
        if (campaignModel == null) return null;

          Campaign campaign = new Campaign();
//        campaign.setId(campaignModel.getId());
//        campaign.setUuid(campaignModel.getUuid());
//        campaign.setTitle(campaignModel.getTitle());
//        campaign.setDescription(campaignModel.getDescription());
//        campaign.setMoneyDonationConfig(MoneyDonationConfigMapper.toDomain(campaignModel.getMoneyDonationConfig()));
//        campaign.setItemDonationConfig(ItemDonationConfigMapper.toDomain(campaignModel.getItemDonationConfig()));

        // Call validate or any domain logic after setting fields
//        try {
//            campaign.validate();
//        } catch (Exception e) {
//            throw new RuntimeException("Campaign validation failed", e);
//        }
//
         return campaign;
    }
}
