package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.DonationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DonationConfigTest {

    @Test
    void shouldCreateDonationConfigWithEnabledTrue() {
        // Arrange
        Long id = 1L;
        String uuid = "config-uuid-123";
        boolean enabled = true;

        // Act
        TestDonationConfig config = new TestDonationConfig(id, uuid, enabled);

        // Assert
        Assertions.assertEquals(id, config.getId());
        Assertions.assertEquals(uuid, config.getUuid());
        Assertions.assertTrue(config.isEnabled());
    }

    @Test
    void shouldCreateDonationConfigWithEnabledFalse() {
        // Arrange
        Long id = 1L;
        String uuid = "config-uuid-123";
        boolean enabled = false;

        // Act
        TestDonationConfig config = new TestDonationConfig(id, uuid, enabled);

        // Assert
        Assertions.assertEquals(id, config.getId());
        Assertions.assertEquals(uuid, config.getUuid());
        Assertions.assertFalse(config.isEnabled());
    }

    @Test
    void shouldCreateDonationConfigWithNullUuid() {
        // Arrange
        Long id = 1L;
        boolean enabled = true;

        // Act
        TestDonationConfig config = new TestDonationConfig(id, null, enabled);

        // Assert
        Assertions.assertEquals(id, config.getId());
        Assertions.assertNotNull(config.getUuid());
        Assertions.assertTrue(config.isEnabled());
    }

    @Test
    void shouldAllowEnabledToBeSet() {
        // Arrange
        TestDonationConfig config = new TestDonationConfig(1L, "uuid", true);

        // Act
        config.setEnabled(false);

        // Assert
        Assertions.assertFalse(config.isEnabled());
    }

    @Test
    void shouldCreateMultipleConfigsWithDifferentSettings() {
        // Arrange & Act
        TestDonationConfig config1 = new TestDonationConfig(1L, "uuid-1", true);
        TestDonationConfig config2 = new TestDonationConfig(2L, "uuid-2", false);
        TestDonationConfig config3 = new TestDonationConfig(3L, "uuid-3", true);

        // Assert
        Assertions.assertTrue(config1.isEnabled());
        Assertions.assertFalse(config2.isEnabled());
        Assertions.assertTrue(config3.isEnabled());
    }

    @Test
    void shouldHandleNullId() {
        // Arrange
        String uuid = "config-uuid-123";
        boolean enabled = true;

        // Act
        TestDonationConfig config = new TestDonationConfig(null, uuid, enabled);

        // Assert
        Assertions.assertNull(config.getId());
        Assertions.assertEquals(uuid, config.getUuid());
        Assertions.assertTrue(config.isEnabled());
    }

    // Concrete implementation for testing abstract DonationConfig class
    private static class TestDonationConfig extends DonationConfig {
        public TestDonationConfig(Long id, String uuid, boolean enabled) {
            super(id, uuid, enabled);
        }
    }
} 