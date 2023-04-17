package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForagerServiceTest {
    ForagerService service = new ForagerService(new ForagerRepositoryDouble());

    @Test
    void shouldADD() throws DataException {
        //ARRANGE
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing1");
        forager.setLastName("Testing2");
        forager.setState("Test");
        //ACT
        Result<Forager> result = service.add(forager);
        //ASSERT
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddDuplicateForager() throws DataException {
        //ARRANGE
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("GA");
        //ACT
        Result<Forager> result = service.add(forager);
        //ASSERT
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNullForager() throws DataException {
        //ARRANGE
        Forager forager = new Forager();
        //ACT
        Result<Forager> result = service.add(forager);
        //ASSERT
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankFirstName() throws DataException {
        //ARRANGE
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("");
        forager.setLastName("Testing2");
        forager.setState("Test");
        //ACT
        Result<Forager> result = service.add(forager);
        //ASSERT
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankLastName() throws DataException {
        //ARRANGE
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing");
        forager.setLastName("");
        forager.setState("Test");
        //ACT
        Result<Forager> result = service.add(forager);
        //ASSERT
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankState() throws DataException {
        //ARRANGE
        Forager forager = new Forager();
        forager.setId("0");
        forager.setFirstName("Testing1");
        forager.setLastName("Testing2");
        forager.setState("");
        //ACT
        Result<Forager> result = service.add(forager);
        //ASSERT
        assertFalse(result.isSuccess());
    }
}