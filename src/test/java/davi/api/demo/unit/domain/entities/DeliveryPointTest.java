package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.DeliveryPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class DeliveryPointTest {

    @Test
    void shouldCreateValidDeliveryPoint() {
        // Arrange & Act
        DeliveryPoint dp = new DeliveryPoint(
                1L,
                "delivery-uuid-123",
                "Rua das Flores",
                123,
                "São Paulo",
                "SP",
                "01234-567",
                LocalTime.of(8, 0),
                LocalTime.of(17, 0)
        );

        // Assert
        Assertions.assertEquals(1L, dp.getId());
        Assertions.assertEquals("delivery-uuid-123", dp.getUuid());
        Assertions.assertEquals("Rua das Flores", dp.getStreet());
        Assertions.assertEquals(123, dp.getNumber());
        Assertions.assertEquals("São Paulo", dp.getCity());
        Assertions.assertEquals("SP", dp.getState());
        Assertions.assertEquals("01234-567", dp.getZipCode());
        Assertions.assertEquals(LocalTime.of(8, 0), dp.getOpeningTime());
        Assertions.assertEquals(LocalTime.of(17, 0), dp.getClosingTime());
    }

    @Test
    void shouldCreateDeliveryPointWithNullUuid() {
        // Arrange & Act
        DeliveryPoint dp = new DeliveryPoint(
                1L,
                null,
                "Rua das Flores",
                123,
                "São Paulo",
                "SP",
                "01234-567",
                LocalTime.of(8, 0),
                LocalTime.of(17, 0)
        );

        // Assert
        Assertions.assertEquals(1L, dp.getId());
        Assertions.assertNotNull(dp.getUuid());
        Assertions.assertEquals("Rua das Flores", dp.getStreet());
    }

    @Test
    void shouldCreateDeliveryPointWithNullId() {
        // Arrange & Act
        DeliveryPoint dp = new DeliveryPoint(
                null,
                "delivery-uuid-123",
                "Rua das Flores",
                123,
                "São Paulo",
                "SP",
                "01234-567",
                LocalTime.of(8, 0),
                LocalTime.of(17, 0)
        );

        // Assert
        Assertions.assertNull(dp.getId());
        Assertions.assertEquals("delivery-uuid-123", dp.getUuid());
        Assertions.assertEquals("Rua das Flores", dp.getStreet());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    null,
                    123,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("Street must not be blank and must be at most 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsBlank() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "   ",
                    123,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("Street must not be blank and must be at most 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsTooLong() {
        // Arrange
        String longStreet = "A".repeat(101);

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    longStreet,
                    123,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("Street must not be blank and must be at most 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNumberIsZero() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    0,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("Number must be greater than 0.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNumberIsNegative() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    -1,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("Number must be greater than 0.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCityIsNull() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    null,
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("City must not be blank and must be at most 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCityIsBlank() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "   ",
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("City must not be blank and must be at most 100 characters.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStateIsNull() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "São Paulo",
                    null,
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("State must be a 2-letter code.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStateIsNotTwoLetters() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "São Paulo",
                    "SPA",
                    "01234-567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("State must be a 2-letter code.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodeIsInvalid() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "São Paulo",
                    "SP",
                    "01234567",
                    LocalTime.of(8, 0),
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("Zip code must follow the format 12345-678.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOpeningTimeIsNull() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    null,
                    LocalTime.of(17, 0)
            );
        });
        Assertions.assertEquals("Opening and closing times must not be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenClosingTimeIsNull() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(8, 0),
                    null
            );
        });
        Assertions.assertEquals("Opening and closing times must not be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOpeningTimeIsAfterClosingTime() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(18, 0),
                    LocalTime.of(9, 0)
            );
        });
        Assertions.assertEquals("Opening time must be before closing time.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOpeningTimeEqualsClosingTime() {
        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryPoint(
                    1L,
                    "delivery-uuid-123",
                    "Rua das Flores",
                    123,
                    "São Paulo",
                    "SP",
                    "01234-567",
                    LocalTime.of(9, 0),
                    LocalTime.of(9, 0)
            );
        });
        Assertions.assertEquals("Opening time must be before closing time.", exception.getMessage());
    }

    @Test
    void shouldCreateDeliveryPointWithValidTimeRange() {
        // Arrange & Act
        DeliveryPoint dp = new DeliveryPoint(
                1L,
                "delivery-uuid-123",
                "Rua das Flores",
                123,
                "São Paulo",
                "SP",
                "01234-567",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)
        );

        // Assert
        Assertions.assertEquals(LocalTime.of(9, 0), dp.getOpeningTime());
        Assertions.assertEquals(LocalTime.of(18, 0), dp.getClosingTime());
    }

    @Test
    void shouldCreateDeliveryPointWithMaximumLengthFields() {
        // Arrange
        String longStreet = "A".repeat(100);
        String longCity = "B".repeat(100);

        // Act
        DeliveryPoint dp = new DeliveryPoint(
                1L,
                "delivery-uuid-123",
                longStreet,
                123,
                longCity,
                "SP",
                "01234-567",
                LocalTime.of(8, 0),
                LocalTime.of(17, 0)
        );

        // Assert
        Assertions.assertEquals(longStreet, dp.getStreet());
        Assertions.assertEquals(longCity, dp.getCity());
    }
}
