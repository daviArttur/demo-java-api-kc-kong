package davi.api.demo.application.useCases;

import davi.api.demo.application.dtos.ApplicationCreateItemDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationCreateMoneyDonationConfigDto;
import davi.api.demo.application.inputs.CreateCampaignInput;
import davi.api.demo.domain.builders.CampaignBuilder;

import davi.api.demo.domain.dtos.DomainCampaignBasicDataDto;
import davi.api.demo.domain.dtos.DomainCreateItemDonationConfigDto;
import davi.api.demo.domain.dtos.DomainCreateItemDto;
import davi.api.demo.domain.dtos.DomainCreateMoneyDonationConfigDto;
import davi.api.demo.domain.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCampaignUseCase {
    private final CampaignRepository campaignRepository;

    @Autowired
    public CreateCampaignUseCase(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public void perform(CreateCampaignInput input) {
        var basicDataDto = input.createBasicCampaignDataDto();
        var itemConfigDto = input.itemDonationConfigDto();
        var moneyConfigDto = input.moneyDonationConfigDto();

        var campaignBuilder = new CampaignBuilder();

        campaignBuilder.withBasicCampaignData(new DomainCampaignBasicDataDto(
                basicDataDto.title(),
                basicDataDto.description()
        ));

        if (itemConfigDto != null) {
            addItemDonationConfig(itemConfigDto, campaignBuilder);
        }

        if (moneyConfigDto != null) {
            addMoneyDonationConfig(moneyConfigDto, campaignBuilder);
        }

        var campaign = campaignBuilder.build();
        campaignRepository.save(campaign);
    }

    private void addItemDonationConfig(ApplicationCreateItemDonationConfigDto dto, CampaignBuilder builder) {
        var createItemsDto = dto.items().stream()
                .map(i -> new DomainCreateItemDto(i.name(), i.quantityGoal(), i.quantityReceived()))
                .toList();

        var itemDonationConfig = new DomainCreateItemDonationConfigDto(dto.enabled(), createItemsDto);
        builder.withItemDonationConfig(itemDonationConfig);
    }

    private void addMoneyDonationConfig(ApplicationCreateMoneyDonationConfigDto dto, CampaignBuilder builder) {
        var config = new DomainCreateMoneyDonationConfigDto(dto.enabled(), dto.useDescription(), dto.goal());
        builder.withMoneyDonationConfig(config);
    }
}
