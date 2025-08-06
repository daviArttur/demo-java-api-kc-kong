package davi.api.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DonationConfig extends Entity {
    private boolean enabled;

    public DonationConfig(Long id, String uuid, boolean enabled) {
        super(id, uuid);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
