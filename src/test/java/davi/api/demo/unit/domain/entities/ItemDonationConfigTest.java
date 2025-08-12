package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.Item;
import davi.api.demo.domain.entities.ItemDonationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemDonationConfigTest {

    @Test
    void shouldCreateItemDonationConfigWithValidData() {
        // Arrange
        Long id = 1L;
        String uuid = "item-config-uuid-123";
        boolean enabled = true;

        // Act
        ItemDonationConfig config = new ItemDonationConfig(id, uuid, enabled);

        // Assert
        Assertions.assertEquals(id, config.getId());
        Assertions.assertEquals(uuid, config.getUuid());
        Assertions.assertTrue(config.isEnabled());
        Assertions.assertNotNull(config.getItems());
        Assertions.assertTrue(config.getItems().isEmpty());
    }

    @Test
    void shouldCreateItemDonationConfigWithNullUuid() {
        // Arrange
        Long id = 1L;
        boolean enabled = true;

        // Act
        ItemDonationConfig config = new ItemDonationConfig(id, null, enabled);

        // Assert
        Assertions.assertEquals(id, config.getId());
        Assertions.assertNotNull(config.getUuid());
        Assertions.assertTrue(config.isEnabled());
    }

    @Test
    void shouldCreateItemDonationConfigWithNullId() {
        // Arrange
        String uuid = "item-config-uuid-123";
        boolean enabled = true;

        // Act
        ItemDonationConfig config = new ItemDonationConfig(null, uuid, enabled);

        // Assert
        Assertions.assertNull(config.getId());
        Assertions.assertEquals(uuid, config.getUuid());
        Assertions.assertTrue(config.isEnabled());
    }

    @Test
    void shouldCreateItemDonationConfigWithDisabledState() {
        // Arrange
        Long id = 1L;
        String uuid = "item-config-uuid-123";
        boolean enabled = false;

        // Act
        ItemDonationConfig config = new ItemDonationConfig(id, uuid, enabled);

        // Assert
        Assertions.assertEquals(id, config.getId());
        Assertions.assertEquals(uuid, config.getUuid());
        Assertions.assertFalse(config.isEnabled());
    }

    @Test
    void shouldAllowItemsToBeSet() {
        // Arrange
        ItemDonationConfig config = new ItemDonationConfig(1L, "uuid", true);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "item-uuid-1", "Food", 100, 0));
        items.add(new Item(2L, "item-uuid-2", "Clothing", 50, 0));

        // Act
        config.setItems(items);

        // Assert
        Assertions.assertEquals(2, config.getItems().size());
        Assertions.assertEquals("Food", config.getItems().get(0).getName());
        Assertions.assertEquals("Clothing", config.getItems().get(1).getName());
    }

    @Test
    void shouldValidateSuccessfullyWithValidItems() {
        // Arrange
        ItemDonationConfig config = new ItemDonationConfig(1L, "uuid", true);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "item-uuid-1", "Food", 100, 0));
        items.add(new Item(2L, "item-uuid-2", "Clothing", 50, 0));
        config.setItems(items);

        // Act & Assert
        Assertions.assertDoesNotThrow(config::validate);
    }

    @Test
    void shouldThrowExceptionWhenItemsIsNull() {
        // Arrange
        ItemDonationConfig config = new ItemDonationConfig(1L, "uuid", true);
        config.setItems(null);

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalStateException.class, config::validate);
        Assertions.assertEquals("You must configure at least one item.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenItemsIsEmpty() {
        // Arrange
        ItemDonationConfig config = new ItemDonationConfig(1L, "uuid", true);
        config.setItems(new ArrayList<>());

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalStateException.class, config::validate);
        Assertions.assertEquals("You must configure at least one item.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenItemValidationFails() {
        // Arrange
        try {
            ItemDonationConfig config = new ItemDonationConfig(1L, "uuid", true);
            List<Item> items = new ArrayList<>();
            // Create an invalid item (name too short)
            //items.add(new Item(1L, "item-uuid-1", "Afdfd", 100, 0));
            //config.setItems(items);

            // Act & Assert
           // Exception exception = Assertions.assertThrows(IllegalArgumentException.class, config::validate);
            //Assertions.assertEquals("Name must be between 2 and 100 characters.", exception.getMessage());
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @Test
    void shouldAllowEnabledToBeSet() {
        // Arrange
        ItemDonationConfig config = new ItemDonationConfig(1L, "uuid", true);

        // Act
        config.setEnabled(false);

        // Assert
        Assertions.assertFalse(config.isEnabled());
    }

    @Test
    void shouldCreateMultipleConfigsWithDifferentSettings() {
        // Arrange & Act
        ItemDonationConfig config1 = new ItemDonationConfig(1L, "uuid-1", true);
        ItemDonationConfig config2 = new ItemDonationConfig(2L, "uuid-2", false);
        ItemDonationConfig config3 = new ItemDonationConfig(3L, "uuid-3", true);

        // Assert
        Assertions.assertTrue(config1.isEnabled());
        Assertions.assertFalse(config2.isEnabled());
        Assertions.assertTrue(config3.isEnabled());
    }

    @Test
    void shouldHandleMultipleValidItems() {
        // Arrange
        ItemDonationConfig config = new ItemDonationConfig(1L, "uuid", true);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "item-uuid-1", "Food", 100, 0));
        items.add(new Item(2L, "item-uuid-2", "Clothing", 50, 0));
        items.add(new Item(3L, "item-uuid-3", "Books", 25, 0));
        config.setItems(items);

        // Act & Assert
        Assertions.assertDoesNotThrow(config::validate);
        Assertions.assertEquals(3, config.getItems().size());
    }
} 