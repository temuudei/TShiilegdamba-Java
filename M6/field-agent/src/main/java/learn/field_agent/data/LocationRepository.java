package learn.field_agent.data;

import learn.field_agent.models.Location;
import org.springframework.transaction.annotation.Transactional;

public interface LocationRepository {
    @Transactional
    Location findById(int locationId);
    @Transactional
    Location add(Location location);
    @Transactional
    boolean update(Location location);
    @Transactional
    boolean deleteById(int locationId);
}
