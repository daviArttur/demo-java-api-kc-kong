package davi.api.demo.application.inputs;

import davi.api.demo.application.dtos.ApplicationCreateBasicCampaignDataDto;
import davi.api.demo.application.dtos.ApplicationCreateItemDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationCreateMoneyDonationConfigDto;

public record CreateCampaignInput(
        ApplicationCreateBasicCampaignDataDto createBasicCampaignDataDto,
        ApplicationCreateMoneyDonationConfigDto moneyDonationConfigDto,
        ApplicationCreateItemDonationConfigDto itemDonationConfigDto
) {}
