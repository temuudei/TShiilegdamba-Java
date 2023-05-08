package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.field_agent.data.LocationRepository;

import learn.field_agent.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerTest {
    @MockBean
    LocationRepository repository;

    @Autowired
    MockMvc mvc;
    @Test
    void shouldFindById() throws  Exception {
        int actualId = 1;

        Location location = new Location();
        location.setName("Test Location");
        location.setAddress("123 Test Ave.");
        location.setCity("Test City");
        location.setRegion("TEST");
        location.setCountryCode("TS");
        location.setPostalCode("TS-5555");
        location.setLocationId(1);
        location.setAgencyId(1);

        when(repository.findById(actualId)).thenReturn(location);
        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(location);

        mvc.perform(get("/api/location/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldAdd() throws Exception {
        Location actual = new Location();
        actual.setName("Test Location");
        actual.setAddress("123 Test Ave.");
        actual.setCity("Test City");
        actual.setRegion("TEST");
        actual.setCountryCode("TS");
        actual.setPostalCode("TS-5555");
        actual.setLocationId(0);
        actual.setAgencyId(1);

        Location expected = new Location();
        expected.setName("Test Location");
        expected.setAddress("123 Test Ave.");
        expected.setCity("Test City");
        expected.setRegion("TEST");
        expected.setCountryCode("TS");
        expected.setPostalCode("TS-5555");
        expected.setLocationId(1);
        expected.setAgencyId(1);

        when(repository.add(any())).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonIn = jsonMapper.writeValueAsString(actual);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

    }

    @Test
    void shouldUpdate() throws  Exception {
        Location location = new Location();
        location.setName("Test Location");
        location.setAddress("123 Test Ave.");
        location.setCity("Test City");
        location.setRegion("TEST");
        location.setCountryCode("TS");
        location.setPostalCode("TS-5555");
        location.setLocationId(1);
        location.setAgencyId(1);

        when(repository.update(any())).thenReturn(true);

        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(location);

        var request = put("/api/location/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedJson);

        mvc.perform(request)
                .andExpect(status().isOk());
    }
    @Test
    void shouldDeleteById() throws Exception {
        int actualId = 1;
        when(repository.deleteById(actualId)).thenReturn(true);
        mvc.perform(delete("/api/location/1"))
                .andExpect(status().isOk());
    }
}