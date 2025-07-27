package davi.api.demo.application.dtos;

public record ApplicationCreateItemDto(
        String name,
        int quantityGoal,
        int quantityReceived
) {}

