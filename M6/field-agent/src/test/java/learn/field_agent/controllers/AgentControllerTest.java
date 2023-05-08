package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import learn.field_agent.data.AgentRepository;
import learn.field_agent.models.Agent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class AgentControllerTest {
    @MockBean
    AgentRepository repository;
    @Autowired
    MockMvc mvc;
    @Test
    void shouldFindAll() throws Exception {
        Agent actual = new Agent();
        actual.setAgentId(1);
        actual.setFirstName("Test");
        actual.setLastName("Last Name");
        actual.setDob(LocalDate.of(1985, 8, 15));
        actual.setHeightInInches(66);

        Agent expected = new Agent();
        expected.setAgentId(2);
        expected.setFirstName("Test");
        expected.setLastName("Last Name");
        expected.setDob(LocalDate.of(1985, 8, 15));
        expected.setHeightInInches(66);

        List<Agent> agents = List.of(actual, expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        String expectedJson = jsonMapper.writeValueAsString(agents);
        when(repository.findAll()).thenReturn(agents);

        mvc.perform(get("/api/agent"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
    @Test
    void shouldAdd() throws Exception {
        Agent actual = new Agent();
        actual.setAgentId(0);
        actual.setFirstName("Test");
        actual.setMiddleName("Testing");
        actual.setLastName("Last Name");
        actual.setDob(LocalDate.parse("1998-02-24"));
        actual.setHeightInInches(66);

        Agent expected = new Agent();
        expected.setAgentId(1);
        expected.setFirstName("Test");
        expected.setMiddleName("Testing");
        expected.setLastName("Last Name");
        expected.setDob(LocalDate.parse("1998-02-24"));
        expected.setHeightInInches(66);

        when(repository.add(any())).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        String jsonIn = jsonMapper.writeValueAsString(actual);
        String expectedJson = jsonMapper.writeValueAsString(expected);


        var request = post("/api/agent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);
        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldUpdate() throws Exception {
        Agent agent = new Agent();
        agent.setAgentId(1);
        agent.setFirstName("Test");
        agent.setMiddleName("Testing");
        agent.setLastName("Last Name");
        agent.setDob(LocalDate.parse("1998-02-24"));
        agent.setHeightInInches(66);

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String expectedJson = jsonMapper.writeValueAsString(agent);

        when(repository.update(any())).thenReturn(true);

        var request = put("/api/agent/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJson);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindById() throws  Exception {
        int actualId = 1;

        Agent expected = new Agent();
        expected.setAgentId(1);
        expected.setFirstName("Test");
        expected.setMiddleName("Testing");
        expected.setLastName("Last Name");
        expected.setDob(LocalDate.parse("1998-02-24"));
        expected.setHeightInInches(66);

        when(repository.findById(actualId)).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String expectedJson = jsonMapper.writeValueAsString(expected);

        mvc.perform(get("/api/agent/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
    @Test
    void shouldDeleteById() throws  Exception {
        int actualId = 1;
        when(repository.deleteById(1)).thenReturn(true);
        mvc.perform(delete("/api/agent/1"))
                .andExpect(status().isOk());
    }
}