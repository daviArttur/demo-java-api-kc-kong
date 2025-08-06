package davi.api.demo.domain.entities;

import davi.api.demo.domain.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoneyDonation extends Entity {
    private Long amount;
    private PaymentType paymentType;

    public MoneyDonation(Long id, String uuid, Long amount, PaymentType paymentType) {
        super(id, uuid);
        this.amount = amount;
        this.paymentType = paymentType;
        validate();
    }

    public void validate() throws IllegalArgumentException {
        if (this.amount == null || amount < 100) {
            throw new IllegalArgumentException("donation amount must be greater than 100");
        }
    }
}

