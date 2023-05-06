package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {
    @Autowired
    AliasJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }
    @Test
    void shouldFindAll() {
        List<Alias> aliases = repository.findAll();
        assertNotNull(aliases);
    }

    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(1, actual.getAlias_id());
    }

    @Test
    void shouldFindById() {
        Alias alias = new Alias(1, "Tim", "Spy", 1);
        Alias actual = repository.findById(1);
        assertEquals(alias.getName(), actual.getName());
        assertEquals(alias.getAlias_id(), actual.getAlias_id());
    }

    private Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Tim");
        alias.setPersona("Spy");
        alias.setAgent_id(5);
        return alias;
    }

}