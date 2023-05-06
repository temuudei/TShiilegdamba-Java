package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {
    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;
    @Test
    void shouldFindAll() {
        List<Alias> aliases = repository.findAll();
        assertNotNull(aliases);
    }

    @Test
    void shouldFindSpy() {
        Alias expected = new Alias(1, "Tim", "Spy", 1);
        when(repository.findById(1)).thenReturn(expected);
        Alias actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullAlias() {
        Alias alias = new Alias();
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenNameIsEmpty() {
        Alias alias = new Alias();
        alias.setAlias_id(0);
        alias.setPersona("Spy");
        alias.setAgent_id(1);
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenIdIsNotZero() {
        Alias alias = new Alias(1, "Tim", "Spy", 1);
        Result<Alias> result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddDuplicateNameAndEmptyPersona() {
        Alias aliasOne = new Alias(0, "Tim", "", 1);
        Alias aliasTwo = new Alias(1, "Tim", "", 1);
        when(repository.add(aliasOne)).thenReturn(aliasTwo);

        Alias result = repository.add(aliasOne);
        Result<Alias> test = service.add(result);
        assertEquals(ResultType.INVALID, test.getType());
    }

    @Test
    void shouldNotAddDuplicateNameAndDuplicatePersona() {
        Alias aliasOne = new Alias(0, "Tim", "Spy", 1);
        Alias aliasTwo = new Alias(1, "Tim", "Spy", 1);
        when(repository.add(aliasOne)).thenReturn(aliasTwo);

        Alias result = repository.add(aliasOne);
        Result<Alias> test = service.add(result);
        assertEquals(ResultType.INVALID, test.getType());
    }

    @Test
    void shouldNotUpdateNullAlias() {
        Alias alias = new Alias();
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenNameIsEmpty() {
        Alias alias = new Alias();
        alias.setAlias_id(0);
        alias.setPersona("Spy");
        alias.setAgent_id(1);
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenIsIsLessThanZero() {
        Alias alias = new Alias(-1, "Tim", "Spy", 1);
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenIdIsEqualToZero() {
        Alias alias = new Alias(0, "Tim", "Spy", 1);
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenAliasIsNotFound() {
        Alias alias = new Alias(4, "Test", "Spy", 1);
        Result<Alias> result = service.update(alias);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }

    @Test
    void shouldDeleteById() {
        Alias alias = new Alias(1, "Tim", "Spy", 1);
        when(repository.deleteById(alias.getAlias_id())).thenReturn(true);
    }
}