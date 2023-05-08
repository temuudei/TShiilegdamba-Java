package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import learn.field_agent.data.AgencyAgentRepository;
import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AgencyAgentControllerTest {
    @MockBean
    AgencyAgentRepository repository;
    @Autowired
    MockMvc mvc;
    @Test
    void shouldAdd() throws Exception {
        AgencyAgent actual = new AgencyAgent();
        actual.setAgencyId(0);
        actual.setIdentifier("007");
        actual.setActivationDate(LocalDate.parse("2010-02-24"));
        actual.setActive(true);

        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(1);
        securityClearance.setName("Secret");
        actual.setSecurityClearance(securityClearance);

        Agent agent = new Agent();
        agent.setAgentId(6);
        agent.setFirstName("Test");
        actual.setAgent(agent);

        when(repository.add(any())).thenReturn(true);

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        String jsonIn = jsonMapper.writeValueAsString(actual);

        var request = post("/api/agency/agent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception{
        AgencyAgent actual = new AgencyAgent();
        actual.setAgencyId(1);
        actual.setIdentifier("007");
        actual.setActivationDate(LocalDate.parse("2010-02-24"));
        actual.setActive(true);

        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(1);
        securityClearance.setName("Secret");
        actual.setSecurityClearance(securityClearance);

        Agent agent = new Agent();
        agent.setAgentId(6);
        agent.setFirstName("Test");
        actual.setAgent(agent);

        when(repository.update(any())).thenReturn(true);

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        String jsonIn = jsonMapper.writeValueAsString(actual);

        var request = put("/api/agency/agent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void deleteByKey() throws Exception {
        int agencyId = 1;
        int agentId = 1;
        when(repository.deleteByKey(agencyId,agentId)).thenReturn(true);
        mvc.perform(delete("/api/agency/agent/1/1"))
                .andExpect(status().isOk());
    }
}