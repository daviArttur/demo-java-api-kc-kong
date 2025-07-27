package davi.api.demo.domain.dtos;

import davi.api.demo.domain.entities.Item;

import java.util.List;

public record DomainCreateItemDonationConfigDto(boolean enabled, List<DomainCreateItemDto> createItemsDto) { }
