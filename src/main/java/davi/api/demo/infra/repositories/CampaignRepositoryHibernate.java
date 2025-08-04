package davi.api.demo.infra.repositories;

import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.domain.repositories.CampaignRepository;
import davi.api.demo.infra.mappers.CampaignMapper;
import davi.api.demo.infra.repositories.models.CampaignModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

interface CampaignJpaRepository extends JpaRepository<CampaignModel, Long> {}


@Repository
public class CampaignRepositoryHibernate implements CampaignRepository {

    private CampaignJpaRepository campaignJpaRepository;

    @Autowired
    CampaignRepositoryHibernate(CampaignJpaRepository campaignJpaRepository) {
        this.campaignJpaRepository = campaignJpaRepository;
    }

//    @Override
//    public User findUserById(String id) {
//        return null;
//    }


    public void save(Campaign campaign) {
        var campaignModel = CampaignMapper.toEntity(campaign);
        campaignJpaRepository.save(campaignModel);
    }

    @Override
    public List<Campaign> findAll() {
        return  campaignJpaRepository.findAll().stream().map(CampaignMapper::toDomain).toList();
    }

    //public Object findUserById(String userId);
}
