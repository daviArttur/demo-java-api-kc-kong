package davi.api.demo.application.dtos;

public record ApplicationMoneyDonationConfigDto(
        boolean enabled,
        Long goal,
        String useDescription
) {} 