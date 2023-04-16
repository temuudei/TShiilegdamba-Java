package learn.foraging.data;

import learn.foraging.domain.Result;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    static final String TEST_DIR_PATH = "./data/foragers.csv";
    ForagerFileRepository repo = new ForagerFileRepository(TEST_DIR_PATH);

    @Test
    void shouldFindAll() {
        List<Forager> all = repo.findAll();
        assertEquals(1008, all.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing1");
        forager.setLastName("Testing2");
        forager.setState("");
        forager = repo.add(forager);
        assertEquals("Testing1", forager.getFirstName());
    }

    @Test
    void shouldFindById() {
        String id = "0e4707f4-407e-4ec9-9665-baca0aabe88c";
        Forager forager = repo.findById(id);
        assertEquals("Jilly", forager.getFirstName());
    }

    @Test
    void shouldFindByState() {
        String state = "GA";
        List<Forager> foragers = repo.findByState(state);
        assertEquals("Jilly", foragers.get(0).getFirstName());
        assertEquals("Shelby", foragers.get(1).getFirstName());
    }
}