package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AliasRepository {
    @Transactional
    List<Alias> findAll();
    @Transactional
    Alias findById(int aliasId);

    @Transactional
    Alias add(Alias alias);

    @Transactional
    boolean update(Alias alias);

    @Transactional
    boolean deleteById(int aliasId);
}
