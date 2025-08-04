package davi.api.demo.domain.repositories;

import davi.api.demo.domain.entities.Campaign;
import java.util.List;

public interface CampaignRepository {
    void save(Campaign campaign);
    List<Campaign> findAll();
}
