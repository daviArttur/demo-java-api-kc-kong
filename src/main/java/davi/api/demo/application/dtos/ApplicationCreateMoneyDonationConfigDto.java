package davi.api.demo.application.dtos;

public record ApplicationCreateMoneyDonationConfigDto(
        boolean enabled,
        int goal,
        String useDescription
) {}
