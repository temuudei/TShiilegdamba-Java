package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AliasControllerTest {
    @MockBean
    AliasRepository repository;
    @Autowired
    MockMvc mvc;

    @Test
    void shouldGetAll() throws Exception {
        List<Alias> aliases = List.of(
                new Alias(1, "Tim", "Spy", 1),
                new Alias(2, "John", "Assassin", 1)
        );

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(aliases);

        when(repository.findAll()).thenReturn(aliases);

        mvc.perform(get("/api/alias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldAdd() throws Exception {
        Alias alias = new Alias(0, "Tim", "Spy", 1);
        Alias expected = new Alias(1, "Tim", "Spy", 1);
        when(repository.add(any())).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(alias);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/alias")
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
        Alias expected = new Alias(1, "Tim", "Spy", 1);
        when(repository.findById(actualId)).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(expected);

        mvc.perform(get("/api/alias/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldUpdate() throws Exception {
        Alias alias = new Alias(1, "Tim", "Spy", 1);
        when(repository.update(alias)).thenReturn(true);

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(alias);

        var request = put("/api/alias/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJson);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        int alias_id = 1;
        when(repository.deleteById(alias_id)).thenReturn(true);
        mvc.perform(delete("/api/alias/1"))
                .andExpect(status().isOk());
    }
}