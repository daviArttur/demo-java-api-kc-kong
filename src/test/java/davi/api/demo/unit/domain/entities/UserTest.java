package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void shouldCreateUserWithValidData() {
        // Arrange
        Long id = 1L;
        String uuid = "user-uuid-123";
        String email = "test@example.com";

        // Act
        User user = new User(id, uuid, email);

        // Assert
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(uuid, user.getUuid());
        Assertions.assertEquals(email, user.email);
    }

    @Test
    void shouldCreateUserWithNullUuid() {
        // Arrange
        Long id = 1L;
        String email = "test@example.com";

        // Act
        User user = new User(id, null, email);

        // Assert
        Assertions.assertEquals(id, user.getId());
        Assertions.assertNotNull(user.getUuid());
        Assertions.assertEquals(email, user.email);
    }

    @Test
    void shouldCreateUserWithEmptyUuid() {
        // Arrange
        Long id = 1L;
        String email = "test@example.com";

        // Act
        User user = new User(id, "", email);

        // Assert
        Assertions.assertEquals(id, user.getId());
        Assertions.assertNotNull(user.getUuid());
        Assertions.assertEquals(email, user.email);
    }

    @Test
    void shouldCreateUserWithNullId() {
        // Arrange
        String uuid = "user-uuid-123";
        String email = "test@example.com";

        // Act
        User user = new User(null, uuid, email);

        // Assert
        Assertions.assertNull(user.getId());
        Assertions.assertEquals(uuid, user.getUuid());
        Assertions.assertEquals(email, user.email);
    }

    @Test
    void shouldCreateUserWithLongEmail() {
        // Arrange
        Long id = 1L;
        String uuid = "user-uuid-123";
        String email = "very.long.email.address@very.long.domain.example.com";

        // Act
        User user = new User(id, uuid, email);

        // Assert
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(uuid, user.getUuid());
        Assertions.assertEquals(email, user.email);
    }

    @Test
    void shouldCreateUserWithSpecialCharactersInEmail() {
        // Arrange
        Long id = 1L;
        String uuid = "user-uuid-123";
        String email = "test+tag@example.com";

        // Act
        User user = new User(id, uuid, email);

        // Assert
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(uuid, user.getUuid());
        Assertions.assertEquals(email, user.email);
    }

    @Test
    void shouldCreateMultipleUsersWithDifferentData() {
        // Arrange & Act
        User user1 = new User(1L, "uuid-1", "user1@example.com");
        User user2 = new User(2L, "uuid-2", "user2@example.com");
        User user3 = new User(3L, "uuid-3", "user3@example.com");

        // Assert
        Assertions.assertEquals(1L, user1.getId());
        Assertions.assertEquals("uuid-1", user1.getUuid());
        Assertions.assertEquals("user1@example.com", user1.email);

        Assertions.assertEquals(2L, user2.getId());
        Assertions.assertEquals("uuid-2", user2.getUuid());
        Assertions.assertEquals("user2@example.com", user2.email);

        Assertions.assertEquals(3L, user3.getId());
        Assertions.assertEquals("uuid-3", user3.getUuid());
        Assertions.assertEquals("user3@example.com", user3.email);
    }
} 