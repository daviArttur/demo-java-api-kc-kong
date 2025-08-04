package davi.api.demo.api.responses;

import java.time.LocalDateTime;

public record CreateCampaignResponse(
        String uuid,
        String title,
        String description,
        LocalDateTime createdAt
) {} 