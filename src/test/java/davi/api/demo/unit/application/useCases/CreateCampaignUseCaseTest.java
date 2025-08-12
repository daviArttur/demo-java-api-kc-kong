package davi.api.demo.unit.application.useCases;

import davi.api.demo.application.dtos.ApplicationCreateItemDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationCreateMoneyDonationConfigDto;
import davi.api.demo.application.inputs.CreateCampaignInput;
import davi.api.demo.application.useCases.CreateCampaignUseCase;
import davi.api.demo.dataFactory.CampaignDataFactory;
import davi.api.demo.domain.builders.CampaignBuilder;
import davi.api.demo.domain.dtos.DomainCampaignBasicDataDto;
import davi.api.demo.domain.dtos.DomainCreateItemDonationConfigDto;
import davi.api.demo.domain.dtos.DomainCreateItemDto;
import davi.api.demo.domain.dtos.DomainCreateMoneyDonationConfigDto;
import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.domain.repositories.CampaignRepository;
import davi.api.demo.infra.repositories.CampaignRepositoryHibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CreateCampaignUseCaseTest {

    private CampaignRepository campaignRepository;
    private CreateCampaignUseCase useCase;

    @BeforeEach
    public void setUp() {
        campaignRepository = mock(CampaignRepositoryHibernate.class);
        useCase = new CreateCampaignUseCase(campaignRepository);
    }

    @Test
    public void shouldCreateCampaignWithAllDonationOptions() {
        var input = new CreateCampaignInput(
                CampaignDataFactory.getBasicCampaignData(),
                CampaignDataFactory.getMoneyDonationConfig(),
                CampaignDataFactory.getItemDonationConfig()
        );

        performAndVerify(input);
    }

    @Test
    public void shouldCreateCampaignWithMoneyDonationOnly() {
        var input = new CreateCampaignInput(
                CampaignDataFactory.getBasicCampaignData(),
                CampaignDataFactory.getMoneyDonationConfig(),
                null
        );

        performAndVerify(input);
    }

    @Test
    public void shouldCreateCampaignWithItemDonationOnly() {
        var input = new CreateCampaignInput(
                CampaignDataFactory.getBasicCampaignData(),
                null,
                CampaignDataFactory.getItemDonationConfig()
        );

        performAndVerify(input);
    }

    private void performAndVerify(CreateCampaignInput input) {
        // Act
        useCase.perform(input);

        // Arrange expected campaign
        var expectedCampaign = buildExpectedCampaign(input);

        // Capture and assert
        ArgumentCaptor<Campaign> captor = ArgumentCaptor.forClass(Campaign.class);
        verify(campaignRepository, atLeastOnce()).save(captor.capture());

        assertThat(captor.getValue())
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes(".*uuid")
                .isEqualTo(expectedCampaign);
    }

    private Campaign buildExpectedCampaign(CreateCampaignInput input) {
        var builder = new CampaignBuilder();

        var basicData = input.createBasicCampaignDataDto();
        builder.withBasicCampaignData(new DomainCampaignBasicDataDto(
                basicData.title(),
                basicData.description()
        ));

        Optional.ofNullable(input.itemDonationConfigDto())
                .ifPresent(dto -> addItemDonationConfig(dto, builder));

        Optional.ofNullable(input.moneyDonationConfigDto())
                .ifPresent(dto -> addMoneyDonationConfig(dto, builder));

        return builder.build();
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