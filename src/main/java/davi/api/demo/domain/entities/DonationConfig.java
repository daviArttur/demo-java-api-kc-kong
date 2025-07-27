package davi.api.demo.domain.entities;

public abstract class DonationConfig extends Entity {
    private boolean enabled;

    public DonationConfig(Long id, String uuid, boolean enabled) {
        super(id, uuid);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
    }
}
