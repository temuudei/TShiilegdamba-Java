package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityClearanceControllerTest {
    @MockBean
    SecurityClearanceRepository repository;
    @Autowired
    MockMvc mvc;

    @Test
    void shouldGetAll() throws Exception {
        List<SecurityClearance> clearances = List.of(
                new SecurityClearance(1, "Secret"),
                new SecurityClearance(2, "Top Secret")
        );

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(clearances);

        when(repository.findAll()).thenReturn(clearances);

        mvc.perform(get("/api/security_clearance"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

    }

    @Test
    void shouldAdd() throws Exception {
        SecurityClearance clearance = new SecurityClearance(0, "Secret");
        clearance.setAgents(new ArrayList<>());
        SecurityClearance expected = new SecurityClearance(1, "Secret");
        expected.setAgents(new ArrayList<>());

        when(repository.add(any())).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(clearance);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/security_clearance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldFindById() throws Exception {
        int actualId = 1;
        SecurityClearance expected = new SecurityClearance(1, "Secret");
        expected.setAgents(new ArrayList<>());
        when(repository.findById(actualId)).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(expected);

        mvc.perform(get("/api/security_clearance/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldUpdate() throws Exception {
        SecurityClearance clearance = new SecurityClearance(1, "Secret 2");
        clearance.setAgents(new ArrayList<>());

        when(repository.update(clearance)).thenReturn(true);

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(clearance);

        var request = put("/api/security_clearance/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJson);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteById() throws Exception {
        int securityClearanceId = 1;
        when(repository.deleteById(securityClearanceId)).thenReturn(true);

        mvc.perform(delete("/api/security_clearance/1"))
                .andExpect(status().isOk());
    }
}