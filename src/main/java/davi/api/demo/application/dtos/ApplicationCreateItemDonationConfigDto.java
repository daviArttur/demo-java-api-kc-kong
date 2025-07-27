package davi.api.demo.application.dtos;

import java.util.List;

public record ApplicationCreateItemDonationConfigDto(
        boolean enabled,
        List<ApplicationCreateItemDto> items
) {}
