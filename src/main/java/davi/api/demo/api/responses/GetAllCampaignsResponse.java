package davi.api.demo.api.responses;

import java.time.LocalDateTime;
import java.util.List;

public record GetAllCampaignsResponse(
        List<CampaignResponse> campaigns
) {
    
    public record CampaignResponse(
            Long id,
            String uuid,
            String title,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            MoneyDonationConfigResponse moneyDonationConfig,
            ItemDonationConfigResponse itemDonationConfig
    ) {}
    
    public record MoneyDonationConfigResponse(
            boolean enabled,
            Long goal,
            String useDescription
    ) {}
    
    public record ItemDonationConfigResponse(
            boolean enabled,
            List<ItemResponse> items
    ) {}
    
    public record ItemResponse(
            Long id,
            String uuid,
            String name,
            int quantityGoal,
            int quantityReceived,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
} 