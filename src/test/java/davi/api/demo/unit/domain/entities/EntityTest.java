package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EntityTest {

    @Test
    void shouldCreateEntityWithProvidedUuid() {
        // Arrange
        Long id = 1L;
        String uuid = "test-uuid-123";

        // Act
        TestEntity entity = new TestEntity(id, uuid);

        // Assert
        Assertions.assertEquals(id, entity.getId());
        Assertions.assertEquals(uuid, entity.getUuid());
    }

    @Test
    void shouldGenerateUuidWhenNullIsProvided() {
        // Arrange
        Long id = 1L;

        // Act
        TestEntity entity = new TestEntity(id, null);

        // Assert
        Assertions.assertEquals(id, entity.getId());
        Assertions.assertNotNull(entity.getUuid());
        Assertions.assertTrue(isValidUuid(entity.getUuid()));
    }

    @Test
    void shouldGenerateUuidWhenEmptyStringIsProvided() {
        // Arrange
        Long id = 1L;

        // Act
        TestEntity entity = new TestEntity(id, "");

        // Assert
        Assertions.assertEquals(id, entity.getId());
        Assertions.assertNotNull(entity.getUuid());
        Assertions.assertTrue(isValidUuid(entity.getUuid()));
    }

    @Test
    void shouldSetUuidAndIdCorrectly() {
        // Arrange
        Long id = 999L;
        String uuid = "custom-uuid-456";

        // Act
        TestEntity entity = new TestEntity(id, uuid);

        // Assert
        Assertions.assertEquals(id, entity.getId());
        Assertions.assertEquals(uuid, entity.getUuid());
    }

    @Test
    void shouldAllowUuidToBeSet() {
        // Arrange
        TestEntity entity = new TestEntity(1L, "original-uuid");
        String newUuid = "new-uuid-789";

        // Act
        entity.setUuid(newUuid);

        // Assert
        Assertions.assertEquals(newUuid, entity.getUuid());
    }

    private boolean isValidUuid(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Concrete implementation for testing abstract Entity class
    private static class TestEntity extends Entity {
        public TestEntity(Long id, String uuid) {
            super(id, uuid);
        }
    }
} 