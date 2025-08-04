package davi.api.demo.api.requests;

import jakarta.validation.Valid;
import java.util.List;

public record CreateCampaignRequest(
        String title,
        String description,
        @Valid
        MoneyDonationConfigRequest moneyDonationConfig,
        @Valid
        ItemDonationConfigRequest itemDonationConfig
) {
    
    public record MoneyDonationConfigRequest(
            Boolean enabled,
            Long goal,
            String useDescription
    ) {}
    
    public record ItemDonationConfigRequest(
            Boolean enabled,
            List<ItemRequest> items
    ) {}
    
    public record ItemRequest(
            String name,
            Integer quantityGoal,
            Integer quantityReceived
    ) {}
} 