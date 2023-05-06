package learn.field_agent.data;

import learn.field_agent.models.Agency;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AgencyRepository {
    @Transactional
    List<Agency> findAll();

    @Transactional
    Agency findById(int agencyId);
    @Transactional
    Agency add(Agency agency);
    @Transactional
    boolean update(Agency agency);

    @Transactional
    boolean deleteById(int agencyId);
}
