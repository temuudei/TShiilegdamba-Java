package org.example.domains;

import org.example.dal.PetRepository;
import org.example.models.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PetServiceTest {
    @MockBean
    PetRepository repository;
    PetService service;
    @Test
    void shouldAdd() {
        // Arrange
        Pet petIn = new Pet(0, "Ule", "Mantis");
        Pet petOut = new Pet(1, "Ule", "Mantis");

        // 4. Stub a specific behavior.
        when(repository.add(petIn)).thenReturn(petOut);

        // Act
        Result<Pet> result = service.add(petIn);

        // Assert
        //assertEquals(2, result.getMessages().size());
        //assertEquals(petOut, result.getPayload());
    }
}