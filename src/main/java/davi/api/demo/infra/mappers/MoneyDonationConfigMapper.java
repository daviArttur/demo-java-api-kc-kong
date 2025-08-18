package davi.api.demo.infra.mappers;

import davi.api.demo.domain.entities.MoneyDonationConfig;
import davi.api.demo.infra.models.MoneyDonationConfigModel;

public class MoneyDonationConfigMapper {

    public static MoneyDonationConfigModel toEntity(MoneyDonationConfig domain) {
        if (domain == null) return null;

        MoneyDonationConfigModel entity = new MoneyDonationConfigModel();
        entity.setId(domain.getId());
        entity.setUuid(domain.getUuid());
        entity.setEnabled(domain.isEnabled());

        // Convert goal from int (assumed reais) to long (cents)
        entity.setGoal(domain.getGoal());

        entity.setUseDescription(domain.getUseDescription());

        return entity;
    }

    public static MoneyDonationConfig toDomain(MoneyDonationConfigModel entity) {
        if (entity == null) return null;

        MoneyDonationConfig domain = new MoneyDonationConfig(
                entity.getId(),
                entity.getUuid(),
                entity.isEnabled()
        );

        // Convert goalInCents back to int reais
        domain.setGoal(entity.getGoal());

        domain.setUseDescription(entity.getUseDescription());

        return domain;
    }
}
