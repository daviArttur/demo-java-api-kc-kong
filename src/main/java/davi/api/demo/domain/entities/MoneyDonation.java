package davi.api.demo.domain.entities;

import davi.api.demo.domain.enums.PaymentType;

public class MoneyDonation extends Entity {
    private Long amount;
    private PaymentType paymentType;

    public MoneyDonation(Long id, String uuid, Long amount, PaymentType paymentType) {
        super(id, uuid);
        this.amount = amount;
        this.paymentType = paymentType;
        validate();
    }

    public Long getAmount() { return amount; }

    public void setAmount(Long amount) { this.amount = amount; }

    public PaymentType getPaymentType() { return paymentType; }

    public void setPaymentType(PaymentType paymentType) { this.paymentType = paymentType; }

    public void validate() throws IllegalArgumentException {
        if (this.amount == null || amount < 100) {
            throw new IllegalArgumentException("donation amount must be greater than 100");
        }
    }
}

