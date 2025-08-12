package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.Donation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DonationTest {

    @Test
    void shouldCreateDonationWithValidData() {
        // Arrange
        Long id = 1L;
        String uuid = "donation-uuid-123";

        // Act
        Donation donation = new Donation(id, uuid);

        // Assert
        Assertions.assertEquals(id, donation.getId());
        Assertions.assertEquals(uuid, donation.getUuid());
    }

    @Test
    void shouldCreateDonationWithNullUuid() {
        // Arrange
        Long id = 1L;

        // Act
        Donation donation = new Donation(id, null);

        // Assert
        Assertions.assertEquals(id, donation.getId());
        Assertions.assertNotNull(donation.getUuid());
    }

    @Test
    void shouldCreateDonationWithEmptyUuid() {
        // Arrange
        Long id = 1L;

        // Act
        Donation donation = new Donation(id, "");

        // Assert
        Assertions.assertEquals(id, donation.getId());
        Assertions.assertNotNull(donation.getUuid());
    }

    @Test
    void shouldCreateDonationWithNullId() {
        // Arrange
        String uuid = "donation-uuid-123";

        // Act
        Donation donation = new Donation(null, uuid);

        // Assert
        Assertions.assertNull(donation.getId());
        Assertions.assertEquals(uuid, donation.getUuid());
    }

    @Test
    void shouldAllowUuidToBeSet() {
        // Arrange
        Donation donation = new Donation(1L, "original-uuid");
        String newUuid = "new-uuid-789";

        // Act
        donation.setUuid(newUuid);

        // Assert
        Assertions.assertEquals(newUuid, donation.getUuid());
    }

    @Test
    void shouldCreateMultipleDonationsWithDifferentData() {
        // Arrange & Act
        Donation donation1 = new Donation(1L, "uuid-1");
        Donation donation2 = new Donation(2L, "uuid-2");
        Donation donation3 = new Donation(3L, "uuid-3");

        // Assert
        Assertions.assertEquals(1L, donation1.getId());
        Assertions.assertEquals("uuid-1", donation1.getUuid());

        Assertions.assertEquals(2L, donation2.getId());
        Assertions.assertEquals("uuid-2", donation2.getUuid());

        Assertions.assertEquals(3L, donation3.getId());
        Assertions.assertEquals("uuid-3", donation3.getUuid());
    }

    @Test
    void shouldCreateDonationWithZeroId() {
        // Arrange
        Long id = 0L;
        String uuid = "donation-uuid-123";

        // Act
        Donation donation = new Donation(id, uuid);

        // Assert
        Assertions.assertEquals(id, donation.getId());
        Assertions.assertEquals(uuid, donation.getUuid());
    }

    @Test
    void shouldCreateDonationWithNegativeId() {
        // Arrange
        Long id = -1L;
        String uuid = "donation-uuid-123";

        // Act
        Donation donation = new Donation(id, uuid);

        // Assert
        Assertions.assertEquals(id, donation.getId());
        Assertions.assertEquals(uuid, donation.getUuid());
    }
} 