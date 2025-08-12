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

    public void validate() {
        if (this.isEnabled()) {
            if (goal == null || goal <= 0) {
                throw new IllegalArgumentException("Goal must be greater than 0 when donation is enabled.");
            }
            if (useDescription == null || useDescription.isBlank()) {
                throw new IllegalArgumentException("Use description is required when donation is enabled.");
            }
        }
    }
}
