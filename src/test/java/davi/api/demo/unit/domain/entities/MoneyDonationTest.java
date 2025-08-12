package davi.api.demo.unit.domain.entities;

import davi.api.demo.domain.entities.MoneyDonation;
import davi.api.demo.domain.enums.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoneyDonationTest {
    @Test
    void shouldCreateMoneyDonationWithValidData() {
        // Arrange & Act
        MoneyDonation donation = new MoneyDonation(1L, "uuid-123", 500L, PaymentType.PIX);

        // Assert
        Assertions.assertEquals(500L, donation.getAmount());
        Assertions.assertEquals(PaymentType.PIX, donation.getPaymentType());
        Assertions.assertEquals(1L, donation.getId());
        Assertions.assertEquals("uuid-123", donation.getUuid());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new MoneyDonation(1L, "uuid-123", null, PaymentType.PIX)
        );
        Assertions.assertEquals("donation amount must be greater than 100", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsLessThanMinimum() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new MoneyDonation(1L, "uuid-123", 99L, PaymentType.CREDIT_CARD)
        );
        Assertions.assertEquals("donation amount must be greater than 100", exception.getMessage());
    }

    @Test
    void shouldAllowAmountExactlyEqualTo100() {
        // Arrange & Act
        MoneyDonation donation = new MoneyDonation(1L, "uuid-123", 100L, PaymentType.PIX);

        // Assert
        Assertions.assertEquals(100L, donation.getAmount());
        Assertions.assertEquals(PaymentType.PIX, donation.getPaymentType());
    }

    @Test
    void shouldCreateMoneyDonationWithAllPaymentTypes() {
        // Arrange & Act
        MoneyDonation pixDonation = new MoneyDonation(1L, "uuid-1", 500L, PaymentType.PIX);
        MoneyDonation creditDonation = new MoneyDonation(2L, "uuid-2", 1000L, PaymentType.CREDIT_CARD);
        MoneyDonation debitDonation = new MoneyDonation(3L, "uuid-3", 750L, PaymentType.DEBIT_CARD);

        // Assert
        Assertions.assertEquals(PaymentType.PIX, pixDonation.getPaymentType());
        Assertions.assertEquals(PaymentType.CREDIT_CARD, creditDonation.getPaymentType());
        Assertions.assertEquals(PaymentType.DEBIT_CARD, debitDonation.getPaymentType());
    }

    @Test
    void shouldCreateMoneyDonationWithNullUuid() {
        // Arrange & Act
        MoneyDonation donation = new MoneyDonation(1L, null, 500L, PaymentType.PIX);

        // Assert
        Assertions.assertEquals(1L, donation.getId());
        Assertions.assertNotNull(donation.getUuid());
        Assertions.assertEquals(500L, donation.getAmount());
        Assertions.assertEquals(PaymentType.PIX, donation.getPaymentType());
    }

    @Test
    void shouldCreateMoneyDonationWithNullId() {
        // Arrange & Act
        MoneyDonation donation = new MoneyDonation(null, "uuid-123", 500L, PaymentType.PIX);

        // Assert
        Assertions.assertNull(donation.getId());
        Assertions.assertEquals("uuid-123", donation.getUuid());
        Assertions.assertEquals(500L, donation.getAmount());
        Assertions.assertEquals(PaymentType.PIX, donation.getPaymentType());
    }

    @Test
    void shouldAllowAmountAndPaymentTypeToBeSet() {
        // Arrange
        MoneyDonation donation = new MoneyDonation(1L, "uuid-123", 500L, PaymentType.PIX);

        // Act
        donation.setAmount(1000L);
        donation.setPaymentType(PaymentType.CREDIT_CARD);

        // Assert
        Assertions.assertEquals(1000L, donation.getAmount());
        Assertions.assertEquals(PaymentType.CREDIT_CARD, donation.getPaymentType());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsSetToInvalidValue() {
        // Arrange
        MoneyDonation donation = new MoneyDonation(1L, "uuid-123", 500L, PaymentType.PIX);

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            donation.setAmount(50L);
            donation.validate();
        });
        Assertions.assertEquals("donation amount must be greater than 100", exception.getMessage());
    }
}
