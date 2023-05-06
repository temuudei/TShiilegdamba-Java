package learn.field_agent.data;

import learn.field_agent.models.AgencyAgent;
import org.springframework.transaction.annotation.Transactional;

public interface AgencyAgentRepository {
    @Transactional
    boolean add(AgencyAgent agencyAgent);
    @Transactional
    boolean update(AgencyAgent agencyAgent);
    @Transactional
    boolean deleteByKey(int agencyId, int agentId);
}
