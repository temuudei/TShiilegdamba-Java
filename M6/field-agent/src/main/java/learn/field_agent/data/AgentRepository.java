package learn.field_agent.data;

import learn.field_agent.models.Agent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AgentRepository {
    @Transactional
    List<Agent> findAll();
    @Transactional
    Agent findById(int agentId);
    @Transactional
    Agent add(Agent agent);
    @Transactional
    boolean update(Agent agent);
    @Transactional
    boolean deleteById(int agentId);
}
