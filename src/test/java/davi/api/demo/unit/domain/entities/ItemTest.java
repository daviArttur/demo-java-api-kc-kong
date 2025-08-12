package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {
    @Test
    void shouldCreateItemWithValidData() {
        // Arrange & Act
        Item item = new Item(1L, "uuid-123", "Notebook", 10, 3);

        // Assert
        Assertions.assertEquals("Notebook", item.getName());
        Assertions.assertEquals(10, item.getQuantityGoal());
        Assertions.assertEquals(3, item.getQuantityReceived());
        Assertions.assertEquals("uuid-123", item.getUuid());
        Assertions.assertEquals(1L, item.getId());
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooShort() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "uuid-123", "A", 10, 2)
        );
        Assertions.assertEquals("Name must be between 2 and 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "uuid-123", null, 10, 2)
        );
        Assertions.assertEquals("Name must be between 2 and 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenQuantityGoalIsZeroOrNegative() {
        Exception zero = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "uuid-123", "Notebook", 0, 2)
        );
        Assertions.assertEquals("Quantity goal must be greater than 0.", zero.getMessage());

        Exception negative = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "uuid-123", "Notebook", -5, 2)
        );
        Assertions.assertEquals("Quantity goal must be greater than 0.", negative.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenQuantityReceivedIsNegative() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "uuid-123", "Notebook", 10, -1)
        );
        Assertions.assertEquals("Quantity received cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldCreateItemWithNullUuid() {
        // Arrange & Act
        Item item = new Item(1L, null, "Notebook", 10, 3);

        // Assert
        Assertions.assertEquals("Notebook", item.getName());
        Assertions.assertEquals(10, item.getQuantityGoal());
        Assertions.assertEquals(3, item.getQuantityReceived());
        Assertions.assertNotNull(item.getUuid());
        Assertions.assertEquals(1L, item.getId());
    }

    @Test
    void shouldCreateItemWithNullId() {
        // Arrange & Act
        Item item = new Item(null, "uuid-123", "Notebook", 10, 3);

        // Assert
        Assertions.assertEquals("Notebook", item.getName());
        Assertions.assertEquals(10, item.getQuantityGoal());
        Assertions.assertEquals(3, item.getQuantityReceived());
        Assertions.assertEquals("uuid-123", item.getUuid());
        Assertions.assertNull(item.getId());
    }

    @Test
    void shouldCreateItemWithMaximumNameLength() {
        // Arrange & Act
        String longName = "A".repeat(100);
        Item item = new Item(1L, "uuid-123", longName, 10, 3);

        // Assert
        Assertions.assertEquals(longName, item.getName());
        Assertions.assertEquals(10, item.getQuantityGoal());
        Assertions.assertEquals(3, item.getQuantityReceived());
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooLong() {
        // Arrange
        String tooLongName = "A".repeat(101);

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "uuid-123", tooLongName, 10, 2)
        );
        Assertions.assertEquals("Name must be between 2 and 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Item(1L, "uuid-123", "   ", 10, 2)
        );
        Assertions.assertEquals("Name must be between 2 and 100 characters.", exception.getMessage());
    }

    @Test
    void shouldAllowQuantityReceivedToBeZero() {
        // Arrange & Act
        Item item = new Item(1L, "uuid-123", "Notebook", 10, 0);

        // Assert
        Assertions.assertEquals("Notebook", item.getName());
        Assertions.assertEquals(10, item.getQuantityGoal());
        Assertions.assertEquals(0, item.getQuantityReceived());
    }

    @Test
    void shouldAllowQuantityReceivedToEqualGoal() {
        // Arrange & Act
        Item item = new Item(1L, "uuid-123", "Notebook", 10, 10);

        // Assert
        Assertions.assertEquals("Notebook", item.getName());
        Assertions.assertEquals(10, item.getQuantityGoal());
        Assertions.assertEquals(10, item.getQuantityReceived());
    }

    @Test
    void shouldAllowQuantityReceivedToExceedGoal() {
        // Arrange & Act
        Item item = new Item(1L, "uuid-123", "Notebook", 10, 15);

        // Assert
        Assertions.assertEquals("Notebook", item.getName());
        Assertions.assertEquals(10, item.getQuantityGoal());
        Assertions.assertEquals(15, item.getQuantityReceived());
    }

    @Test
    void shouldAllowNameAndQuantitiesToBeSet() {
        // Arrange
        Item item = new Item(1L, "uuid-123", "Original", 10, 5);

        // Act
        item.setName("Updated");
        item.setQuantityGoal(20);
        item.setQuantityReceived(10);

        // Assert
        Assertions.assertEquals("Updated", item.getName());
        Assertions.assertEquals(20, item.getQuantityGoal());
        Assertions.assertEquals(10, item.getQuantityReceived());
    }
}
