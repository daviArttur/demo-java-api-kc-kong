package davi.api.demo.infra.mappers;

import davi.api.demo.domain.entities.ItemDonationConfig;
import davi.api.demo.infra.repositories.models.ItemDonationConfigModel;
import davi.api.demo.infra.repositories.models.ItemModel;

import java.util.List;

public class ItemDonationConfigMapper {

    public static ItemDonationConfigModel toEntity(ItemDonationConfig domain) {
        if (domain == null) return null;

        ItemDonationConfigModel entity = new ItemDonationConfigModel();
        entity.setId(domain.getId());
        entity.setUuid(domain.getUuid());
        entity.setEnabled(domain.isEnabled());

        List<ItemModel> items = ItemMapper.toEntityList(domain.getItems());
        entity.setItems(items);

        // Set back-reference in items to ensure bidirectional integrity
        if (items != null) {
            items.forEach(item -> item.setDonationConfig(entity));
        }

        return entity;
    }

    public static ItemDonationConfig toDomain(ItemDonationConfigModel entity) {
        if (entity == null) return null;

        ItemDonationConfig domain = new ItemDonationConfig(
                entity.getId(),
                entity.getUuid(),
                entity.isEnabled()
        );

        domain.setItems(ItemMapper.toDomainList(entity.getItems()));

        return domain;
    }
}
