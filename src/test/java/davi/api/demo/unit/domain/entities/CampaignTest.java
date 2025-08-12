package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.dtos.DomainCreateCampaignDto;
import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.domain.entities.ItemDonationConfig;
import davi.api.demo.domain.entities.MoneyDonationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CampaignTest {

    @Test
    void shouldCreateCampaignWithValidData() throws Exception {
        // Arrange
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                "uuid-123",
                "Valid Title",
                "Valid description",
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        // Act
        Campaign campaign = new Campaign(dto);

        // Assert
        Assertions.assertEquals("Valid Title", campaign.getTitle());
        Assertions.assertEquals("Valid description", campaign.getDescription());
        Assertions.assertNotNull(campaign.getMoneyDonationConfig());
        Assertions.assertNotNull(campaign.getItemDonationConfig());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsTooShort() {
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                "uuid-123",
                "abc", // muito curto
                "Valid description",
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Campaign(dto));
        Assertions.assertEquals("Title must have between 4 and 40 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsTooLong() {
        String longDescription = "a".repeat(1000); // ultrapassa 999 caracteres
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                "uuid-123",
                "Valid Title",
                longDescription,
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Campaign(dto));
        Assertions.assertEquals("Description must have between 4 and 999 characters.", exception.getMessage());
    }

    @Test
    void shouldCreateCampaignWithDefaultConstructor() {
        // Act
        Campaign campaign = new Campaign();

        // Assert
        Assertions.assertNull(campaign.getId());
        Assertions.assertNotNull(campaign.getUuid());
        Assertions.assertNull(campaign.getTitle());
        Assertions.assertNull(campaign.getDescription());
        Assertions.assertNull(campaign.getMoneyDonationConfig());
        Assertions.assertNull(campaign.getItemDonationConfig());
    }

    @Test
    void shouldCreateCampaignWithNullId() throws Exception {
        // Arrange
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                null,
                "uuid-123",
                "Valid Title",
                "Valid description",
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        // Act
        Campaign campaign = new Campaign(dto);

        // Assert
        Assertions.assertNull(campaign.getId());
        Assertions.assertEquals("uuid-123", campaign.getUuid());
        Assertions.assertEquals("Valid Title", campaign.getTitle());
        Assertions.assertEquals("Valid description", campaign.getDescription());
    }

    @Test
    void shouldCreateCampaignWithNullUuid() throws Exception {
        // Arrange
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                null,
                "Valid Title",
                "Valid description",
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        // Act
        Campaign campaign = new Campaign(dto);

        // Assert
        Assertions.assertEquals(1L, campaign.getId());
        Assertions.assertNotNull(campaign.getUuid());
        Assertions.assertEquals("Valid Title", campaign.getTitle());
        Assertions.assertEquals("Valid description", campaign.getDescription());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsNull() throws Exception {
        // Arrange
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                "uuid-123",
                null,
                "Valid description",
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Campaign(dto));
        Assertions.assertEquals("Title must have between 4 and 40 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsTooLong() throws Exception {
        // Arrange
        String longTitle = "A".repeat(41); // exceeds 40 characters
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                "uuid-123",
                longTitle,
                "Valid description",
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Campaign(dto));
        Assertions.assertEquals("Title must have between 4 and 40 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() throws Exception {
        // Arrange
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                "uuid-123",
                "Valid Title",
                null,
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Campaign(dto));
        Assertions.assertEquals("Description must have between 4 and 999 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsTooShort() throws Exception {
        // Arrange
        DomainCreateCampaignDto dto = new DomainCreateCampaignDto(
                1L,
                "uuid-123",
                "Valid Title",
                "abc", // too short
                new MoneyDonationConfig(1L, "money-uuid", true),
                new ItemDonationConfig(1L, "item-uuid", true)
        );

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Campaign(dto));
        Assertions.assertEquals("Description must have between 4 and 999 characters.", exception.getMessage());
    }

    @Test
    void shouldAllowTitleAndDescriptionToBeSet() {
        // Arrange
        Campaign campaign = new Campaign();
        campaign.setTitle("New Title");
        campaign.setDescription("New description");

        // Act & Assert
        Assertions.assertEquals("New Title", campaign.getTitle());
        Assertions.assertEquals("New description", campaign.getDescription());
    }

    @Test
    void shouldAllowMoneyDonationConfigToBeSet() {
        // Arrange
        Campaign campaign = new Campaign();
        MoneyDonationConfig moneyConfig = new MoneyDonationConfig(1L, "money-uuid", true);

        // Act
        campaign.setMoneyDonationConfig(moneyConfig);

        // Assert
        Assertions.assertEquals(moneyConfig, campaign.getMoneyDonationConfig());
    }

    @Test
    void shouldAllowItemDonationConfigToBeSet() {
        // Arrange
        Campaign campaign = new Campaign();
        ItemDonationConfig itemConfig = new ItemDonationConfig(1L, "item-uuid", true);

        // Act
        campaign.setItemDonationConfig(itemConfig);

        // Assert
        Assertions.assertEquals(itemConfig, campaign.getItemDonationConfig());
    }

    @Test
    void shouldValidateSuccessfullyWithValidData() {
        // Arrange
        Campaign campaign = new Campaign();
        campaign.setTitle("Valid Title");
        campaign.setDescription("Valid description");

        // Act & Assert
        Assertions.assertDoesNotThrow(campaign::validate);
    }
}
