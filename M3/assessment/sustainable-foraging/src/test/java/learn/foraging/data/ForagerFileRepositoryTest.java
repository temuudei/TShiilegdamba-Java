package learn.foraging.data;

import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    static final String TEST_DIR_PATH = "./data/foragers.csv";
    ForagerFileRepository repo = new ForagerFileRepository(TEST_DIR_PATH);

    @Test
    void shouldFindAll() {
        List<Forager> all = repo.findAll();
        assertEquals(1010, all.size());
    }

    @Test
    void shouldAdd() throws DataException {
        //ARRANGE
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing1");
        forager.setLastName("Testing2");
        forager.setState("");
        //ASSERT
        forager = repo.add(forager);
        //ASSERT
        assertEquals("Testing1", forager.getFirstName());
    }

    @Test
    void shouldFindById() {
        //ARRANGE
        String id = "0e4707f4-407e-4ec9-9665-baca0aabe88c";
        //ACT
        Forager forager = repo.findById(id);
        //ASSERT
        assertEquals("Jilly", forager.getFirstName());
    }

    @Test
    void shouldFindByState() {
        //ARRANGE
        String state = "GA";
        //ACT
        List<Forager> foragers = repo.findByState(state);
        //ASSERT
        assertEquals("Jilly", foragers.get(0).getFirstName());
        assertEquals("Shelby", foragers.get(1).getFirstName());
    }
}