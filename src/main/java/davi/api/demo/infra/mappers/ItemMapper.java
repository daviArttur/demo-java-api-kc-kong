package davi.api.demo.infra.mappers;

import davi.api.demo.domain.entities.Item;
import davi.api.demo.infra.models.ItemModel;

import java.util.List;
import java.util.stream.Collectors;


public class ItemMapper {

    public static ItemModel toEntity(Item domain) {
        if (domain == null) return null;

        ItemModel entity = new ItemModel();
        entity.setId(domain.getId());
        entity.setUuid(domain.getUuid());
        entity.setName(domain.getName());
        entity.setQuantityGoal(domain.getQuantityGoal());
        entity.setQuantityReceived(domain.getQuantityReceived());

        // We assume that donationConfig will be set from ItemDonationConfigMapper to maintain integrity
        return entity;
    }

    public static Item toDomain(ItemModel entity) {
        if (entity == null) return null;

        return new Item(
                entity.getId(),
                entity.getUuid(),
                entity.getName(),
                entity.getQuantityGoal(),
                entity.getQuantityReceived()
        );
    }

    public static List<ItemModel> toEntityList(List<Item> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(ItemMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<Item> toDomainList(List<ItemModel> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(ItemMapper::toDomain)
                .collect(Collectors.toList());
    }
}
