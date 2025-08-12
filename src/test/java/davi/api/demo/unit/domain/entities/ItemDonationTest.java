package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.ItemDonation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemDonationTest {

    @Test
    void shouldCreateItemDonationWithValidData() {
        // Arrange
        Long id = 1L;
        String uuid = "item-donation-uuid-123";

        // Act
        ItemDonation itemDonation = new ItemDonation(id, uuid);

        // Assert
        Assertions.assertEquals(id, itemDonation.getId());
        Assertions.assertEquals(uuid, itemDonation.getUuid());
    }

    @Test
    void shouldCreateItemDonationWithNullUuid() {
        // Arrange
        Long id = 1L;

        // Act
        ItemDonation itemDonation = new ItemDonation(id, null);

        // Assert
        Assertions.assertEquals(id, itemDonation.getId());
        Assertions.assertNotNull(itemDonation.getUuid());
    }

    @Test
    void shouldCreateItemDonationWithEmptyUuid() {
        // Arrange
        Long id = 1L;

        // Act
        ItemDonation itemDonation = new ItemDonation(id, "");

        // Assert
        Assertions.assertEquals(id, itemDonation.getId());
        Assertions.assertNotNull(itemDonation.getUuid());
    }

    @Test
    void shouldCreateItemDonationWithNullId() {
        // Arrange
        String uuid = "item-donation-uuid-123";

        // Act
        ItemDonation itemDonation = new ItemDonation(null, uuid);

        // Assert
        Assertions.assertNull(itemDonation.getId());
        Assertions.assertEquals(uuid, itemDonation.getUuid());
    }

    @Test
    void shouldAllowUuidToBeSet() {
        // Arrange
        ItemDonation itemDonation = new ItemDonation(1L, "original-uuid");
        String newUuid = "new-uuid-789";

        // Act
        itemDonation.setUuid(newUuid);

        // Assert
        Assertions.assertEquals(newUuid, itemDonation.getUuid());
    }

    @Test
    void shouldCreateMultipleItemDonationsWithDifferentData() {
        // Arrange & Act
        ItemDonation itemDonation1 = new ItemDonation(1L, "uuid-1");
        ItemDonation itemDonation2 = new ItemDonation(2L, "uuid-2");
        ItemDonation itemDonation3 = new ItemDonation(3L, "uuid-3");

        // Assert
        Assertions.assertEquals(1L, itemDonation1.getId());
        Assertions.assertEquals("uuid-1", itemDonation1.getUuid());

        Assertions.assertEquals(2L, itemDonation2.getId());
        Assertions.assertEquals("uuid-2", itemDonation2.getUuid());

        Assertions.assertEquals(3L, itemDonation3.getId());
        Assertions.assertEquals("uuid-3", itemDonation3.getUuid());
    }

    @Test
    void shouldCreateItemDonationWithZeroId() {
        // Arrange
        Long id = 0L;
        String uuid = "item-donation-uuid-123";

        // Act
        ItemDonation itemDonation = new ItemDonation(id, uuid);

        // Assert
        Assertions.assertEquals(id, itemDonation.getId());
        Assertions.assertEquals(uuid, itemDonation.getUuid());
    }

    @Test
    void shouldCreateItemDonationWithNegativeId() {
        // Arrange
        Long id = -1L;
        String uuid = "item-donation-uuid-123";

        // Act
        ItemDonation itemDonation = new ItemDonation(id, uuid);

        // Assert
        Assertions.assertEquals(id, itemDonation.getId());
        Assertions.assertEquals(uuid, itemDonation.getUuid());
    }

    @Test
    void shouldInheritFromDonation() {
        // Arrange & Act
        ItemDonation itemDonation = new ItemDonation(1L, "test-uuid");

        // Assert
        Assertions.assertTrue(itemDonation instanceof ItemDonation);
        Assertions.assertEquals(1L, itemDonation.getId());
        Assertions.assertEquals("test-uuid", itemDonation.getUuid());
    }
} 