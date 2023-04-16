package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForagerServiceTest {
    ForagerService service = new ForagerService(new ForagerRepositoryDouble());
    @Test
    void shouldADD() throws DataException {
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing1");
        forager.setLastName("Testing2");
        forager.setState("Test");
        Result<Forager> result = service.add(forager);
        assertTrue(result.isSuccess());
    }
    @Test
    void shouldNotAddDuplicateForager() throws DataException {
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("GA");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNullForager() throws DataException {
        Forager forager = new Forager();
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankFirstName() throws DataException {
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("");
        forager.setLastName("Testing2");
        forager.setState("Test");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankLastName() throws DataException {
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing");
        forager.setLastName("");
        forager.setState("Test");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlackState() throws DataException {
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing1");
        forager.setLastName("Testing2");
        forager.setState("");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }
}