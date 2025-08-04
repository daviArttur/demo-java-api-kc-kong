package davi.api.demo.application.dtos;

import java.util.List;

public record ApplicationItemDonationConfigDto(
        boolean enabled,
        List<ApplicationItemDto> items
) {} 