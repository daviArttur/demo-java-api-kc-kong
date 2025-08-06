package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoneyDonationConfig extends DonationConfig {
    private Long goal;
    private String useDescription;

    public MoneyDonationConfig(Long id, String uuid, boolean enabled) {
        super(id, uuid, enabled);
    }
}
