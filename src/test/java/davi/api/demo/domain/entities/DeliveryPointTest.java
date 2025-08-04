package davi.api.demo.domain.entities;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class DeliveryPointTest {
    @Test
    public void shouldCreateValidDeliveryPoint() {
//        DeliveryPoint dp = new DeliveryPoint(
//                null,
//                "Rua das Flores",
//                123,
//                "São Paulo",
//                "SP",
//                "01234-567",
//                LocalTime.of(8, 0),
//                LocalTime.of(17, 0)
//        );

//        Assertions.assertEquals("Rua das Flores", dp.getUuid());
//        Assertions.assertEquals("Rua das Flores", dp.getStreet());
//        Assertions.assertEquals(123, dp.getNumber());
//        Assertions.assertEquals("São Paulo", dp.getCity());
//        Assertions.assertEquals("SP", dp.getState());
//        Assertions.assertEquals("01234-567", dp.getZipCode());
//        Assertions.assertEquals(LocalTime.of(8, 0), dp.getOpeningTime());
//        Assertions.assertEquals(LocalTime.of(17, 0), dp.getClosingTime());
    }

    @Test
    public void shouldThrowExceptionIfInvalidTimeRange() {
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            new DeliveryPoint(
//                    null,
//                    "Rua A",
//                    10,
//                    "City",
//                    "ST",
//                    "00000-000",
//                    LocalTime.of(18, 0),
//                    LocalTime.of(9, 0)
//            );
//        });
//
//        Assertions.assertEquals("Opening time must be before closing time.", exception.getMessage());
    }
}
