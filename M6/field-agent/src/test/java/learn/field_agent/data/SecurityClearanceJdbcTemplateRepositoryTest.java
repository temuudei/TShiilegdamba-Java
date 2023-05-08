package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> clearances = repository.findAll();
        assertNotNull(clearances);
    }

    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret.getName(), actual.getName());
        assertEquals(secret.getSecurityClearanceId(), actual.getSecurityClearanceId());

        actual = repository.findById(2);
        assertEquals(topSecret.getName(), actual.getName());
        assertEquals(topSecret.getSecurityClearanceId(), actual.getSecurityClearanceId());

        actual = repository.findById(4);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        SecurityClearance clearance = makeClearance();
        SecurityClearance actual = repository.add(clearance);
        assertNotNull(actual);
        assertEquals(5, actual.getSecurityClearanceId());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance clearance = makeClearance();
        clearance.setSecurityClearanceId(2);
        assertTrue(repository.update(clearance));
        clearance.setSecurityClearanceId(50);
        assertFalse(repository.update(clearance));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(3));
    }
    private SecurityClearance makeClearance() {
        SecurityClearance clearance = new SecurityClearance();
        clearance.setName("Testing name");
        clearance.setAgents(new ArrayList<>());
        return clearance;
    }
}