package davi.api.demo.unit.domain.builders;


import davi.api.demo.domain.builders.CampaignBuilder;
import davi.api.demo.domain.dtos.DomainCampaignBasicDataDto;
import davi.api.demo.domain.dtos.DomainCreateItemDonationConfigDto;
import davi.api.demo.domain.dtos.DomainCreateItemDto;
import davi.api.demo.domain.dtos.DomainCreateMoneyDonationConfigDto;
import davi.api.demo.unit.config.UnitTest;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag("unit")
public class CampaignBuilderTest extends UnitTest {

    @Test
    public void shouldBuildCampaign() {
        // Arrange
        var domainCampaignBasicDataDto = new DomainCampaignBasicDataDto(
                this.faker.text().text(10, 40),
                this.faker.text().text(10, 40)
        );
        var moneyDonationConfigDto = new DomainCreateMoneyDonationConfigDto(
                this.faker.bool().bool(),
                this.faker.text().text(10, 40),
                this.faker.number().randomNumber(3)
        );
        var createItemDto = new DomainCreateItemDto(
                this.faker.text().text(10, 40),
                (int) this.faker.number().randomNumber(3),
                0
        );
        var itemDonationConfigDto = new DomainCreateItemDonationConfigDto(
                this.faker.bool().bool(),
                new ArrayList<>(List.of(createItemDto))
                );

        // Act
            var builder = new CampaignBuilder();
            builder.withBasicCampaignData(domainCampaignBasicDataDto);
            builder.withMoneyDonationConfig(moneyDonationConfigDto);
            builder.withItemDonationConfig(itemDonationConfigDto);
            var campaign = builder.build();

        // Assert
        Assertions.assertEquals(domainCampaignBasicDataDto.description(), campaign.getDescription());
        Assertions.assertEquals(domainCampaignBasicDataDto.title(), campaign.getTitle());

        var moneyDonationConfig = campaign.getMoneyDonationConfig();
        Assertions.assertEquals(moneyDonationConfigDto.goal(), moneyDonationConfig.getGoal());
        Assertions.assertEquals(moneyDonationConfigDto.useDescription(), moneyDonationConfig.getUseDescription());
        Assertions.assertEquals(moneyDonationConfigDto.enabled(), moneyDonationConfig.isEnabled());

        var itemsDonationConfig = campaign.getItemDonationConfig();
        for (int i = 0; i < itemsDonationConfig.getItems().size(); i++) {
            Assertions.assertEquals(
                    createItemDto.name(),
                    itemsDonationConfig.getItems().get(i).getName()
            );
            Assertions.assertEquals(
                    createItemDto.quantityGoal(),
                    itemsDonationConfig.getItems().get(i).getQuantityGoal()
            );
            Assertions.assertEquals(
                    createItemDto.quantityReceived(),
                    itemsDonationConfig.getItems().get(i).getQuantityReceived()
            );
        }
    }
}
