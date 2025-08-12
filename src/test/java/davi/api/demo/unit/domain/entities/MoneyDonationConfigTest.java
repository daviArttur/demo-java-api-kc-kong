package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.MoneyDonationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoneyDonationConfigTest {
    @Test
    void shouldPassValidationWhenEnabledAndValidData() {
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);
        config.setGoal(1000L);
        config.setUseDescription("Para ajudar nas despesas da instituição.");

        Assertions.assertDoesNotThrow(config::validate);
    }

    @Test
    void shouldNotValidateWhenEnabledAndGoalIsNull() {
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);
        config.setGoal(null);
        config.setUseDescription("Compra de alimentos");

        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, config::validate);
        Assertions.assertEquals("Goal must be greater than 0 when donation is enabled.", ex.getMessage());
    }

    @Test
    void shouldNotValidateWhenEnabledAndGoalIsZeroOrNegative() {
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);
        config.setGoal(0L);
        config.setUseDescription("Compra de mantimentos");

        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, config::validate);
        Assertions.assertEquals("Goal must be greater than 0 when donation is enabled.", ex.getMessage());
    }

    @Test
    void shouldNotValidateWhenEnabledAndDescriptionIsBlank() {
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);
        config.setGoal(1000L);
        config.setUseDescription("   ");

        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, config::validate);
        Assertions.assertEquals("Use description is required when donation is enabled.", ex.getMessage());
    }

    @Test
    void shouldSkipValidationWhenDisabled() {
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", false);
        config.setGoal(null);
        config.setUseDescription(null);

        Assertions.assertDoesNotThrow(config::validate);
    }

    @Test
    void shouldCreateMoneyDonationConfigWithValidData() {
        // Arrange & Act
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);
        config.setGoal(1000L);
        config.setUseDescription("Para ajudar nas despesas da instituição.");

        // Assert
        Assertions.assertEquals(1L, config.getId());
        Assertions.assertEquals("uuid-123", config.getUuid());
        Assertions.assertTrue(config.isEnabled());
        Assertions.assertEquals(1000L, config.getGoal());
        Assertions.assertEquals("Para ajudar nas despesas da instituição.", config.getUseDescription());
    }

    @Test
    void shouldCreateMoneyDonationConfigWithNullUuid() {
        // Arrange & Act
        MoneyDonationConfig config = new MoneyDonationConfig(1L, null, true);

        // Assert
        Assertions.assertEquals(1L, config.getId());
        Assertions.assertNotNull(config.getUuid());
        Assertions.assertTrue(config.isEnabled());
    }

    @Test
    void shouldCreateMoneyDonationConfigWithNullId() {
        // Arrange & Act
        MoneyDonationConfig config = new MoneyDonationConfig(null, "uuid-123", true);

        // Assert
        Assertions.assertNull(config.getId());
        Assertions.assertEquals("uuid-123", config.getUuid());
        Assertions.assertTrue(config.isEnabled());
    }

    @Test
    void shouldAllowGoalAndUseDescriptionToBeSet() {
        // Arrange
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);

        // Act
        config.setGoal(2000L);
        config.setUseDescription("Nova descrição de uso");

        // Assert
        Assertions.assertEquals(2000L, config.getGoal());
        Assertions.assertEquals("Nova descrição de uso", config.getUseDescription());
    }

    @Test
    void shouldNotValidateWhenEnabledAndUseDescriptionIsNull() {
        // Arrange
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);
        config.setGoal(1000L);
        config.setUseDescription(null);

        // Act & Assert
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, config::validate);
        Assertions.assertEquals("Use description is required when donation is enabled.", ex.getMessage());
    }

    @Test
    void shouldNotValidateWhenEnabledAndUseDescriptionIsEmpty() {
        // Arrange
        MoneyDonationConfig config = new MoneyDonationConfig(1L, "uuid-123", true);
        config.setGoal(1000L);
        config.setUseDescription("");

        // Act & Assert
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, config::validate);
        Assertions.assertEquals("Use description is required when donation is enabled.", ex.getMessage());
    }
}
