package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {
    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;
    @Test
    void shouldFindAll() {
        List<SecurityClearance> clearances = repository.findAll();
        assertNotNull(clearances);
    }

    @Test
    void shouldFindSecret() {
        SecurityClearance expected = new SecurityClearance();
        expected.setSecurityClearanceId(1);
        expected.setName("Secret");
        expected.setAgents(new ArrayList<>());

        when(repository.findById(1)).thenReturn(expected);
        SecurityClearance actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullSecurityClearance() {
        SecurityClearance clearance = new SecurityClearance();
        Result<SecurityClearance> result = service.add(clearance);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenNameIsEmpty() {
        SecurityClearance clearance = new SecurityClearance();
        clearance.setSecurityClearanceId(0);
        Result<SecurityClearance> result = service.add(clearance);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenIdIsNotZero() {
        SecurityClearance clearance = new SecurityClearance(1, "Top");
        Result<SecurityClearance> result = service.add(clearance);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddDuplicateName() {
        SecurityClearance clearanceOne = new SecurityClearance(0, "Testing");
        SecurityClearance clearanceTwo = new SecurityClearance(1, "Testing");
        when(repository.add(clearanceOne)).thenReturn(clearanceTwo);

        SecurityClearance result = repository.add(clearanceOne);
        Result<SecurityClearance> test = service.add(result);
        assertEquals(ResultType.INVALID, test.getType());
    }

    @Test
    void shouldNotUpdateNullSecurityClearance() {
        SecurityClearance clearance = new SecurityClearance();
        Result<SecurityClearance> result = service.update(clearance);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenNameIsEmpty() {
        SecurityClearance clearance = new SecurityClearance();
        clearance.setSecurityClearanceId(1);
        Result<SecurityClearance> result = service.update(clearance);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenIdIsLessThanZero() {
        SecurityClearance clearance = new SecurityClearance(-1, "Top");
        Result<SecurityClearance> result = service.update(clearance);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenIdIsEqualToZero() {
        SecurityClearance clearance = new SecurityClearance(0, "Top");
        Result<SecurityClearance> result = service.update(clearance);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenClearanceIsNotFound() {
        SecurityClearance clearance = new SecurityClearance(5, "Hi");
        Result<SecurityClearance> result = service.update(clearance);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }


    @Test
    void shouldDeleteById() {
        SecurityClearance clearance = new SecurityClearance(1, "Top");
        when(repository.deleteById(clearance.getSecurityClearanceId())).thenReturn(true);
    }
}