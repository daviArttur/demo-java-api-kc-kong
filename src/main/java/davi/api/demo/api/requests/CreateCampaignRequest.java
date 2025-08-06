package davi.api.demo.api.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record CreateCampaignRequest(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @Valid
        @NotNull
        MoneyDonationConfigRequest moneyDonationConfig,

        @Valid
        @NotNull
        ItemDonationConfigRequest itemDonationConfig
) {

    public record MoneyDonationConfigRequest(
            @NotNull
            Boolean enabled,

            @NotNull
            @PositiveOrZero
            Long goal,

            @NotBlank
            String useDescription
    ) {}

    public record ItemDonationConfigRequest(
            @NotNull
            Boolean enabled,

            @NotNull
            @Valid
            List<@Valid ItemRequest> items
    ) {}

    public record ItemRequest(
            @NotBlank
            String name,

            @PositiveOrZero
            Integer quantityGoal,

            @PositiveOrZero
            Integer quantityReceived
    ) {}
}