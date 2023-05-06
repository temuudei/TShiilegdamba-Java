package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SecurityClearanceRepository {
    @Transactional
    SecurityClearance findById(int securityClearanceId);
    @Transactional
    List<SecurityClearance> findAll();
    @Transactional
    SecurityClearance add(SecurityClearance securityClearance);
    @Transactional
    boolean update(SecurityClearance securityClearance);
    @Transactional
    boolean deleteById(int securityClearanceId);
}
