package davi.api.demo.domain.entities;

public class MoneyDonationConfig extends DonationConfig {
    private int goal;
    private String useDescription;

    public MoneyDonationConfig(Long id, String uuid, boolean enabled) {
        super(id, uuid, enabled);
    }

    public int getGoal() { return goal; }

    public void setGoal(int goal) { this.goal = goal; }

    public String getUseDescription() { return useDescription; }

    public void setUseDescription(String useDescription) { this.useDescription = useDescription; }
}
