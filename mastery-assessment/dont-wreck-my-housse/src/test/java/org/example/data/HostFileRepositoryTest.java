package org.example.data;

import org.example.models.Host;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {
    static final String DIRECTORY = "./data/hosts.csv";
    static final int HOST_COUNT = 1000;
    HostFileRepository hostFileRepository = new HostFileRepository(DIRECTORY);

    @Test
    void hostSizeShouldBeOneThousand() {
        //ACT
        List<Host> hosts = hostFileRepository.findAll();
        //ASSERT
        assertEquals(HOST_COUNT, hosts.size());
        System.out.println(hosts.get(0).getId());
    }

    @Test
    void veryFirstLastNameShouldEqualToYearnes() {
        //ACT
        List<Host> hosts = hostFileRepository.findAll();
        //ASSERT
        String expected = "Yearnes";
        assertEquals(expected, hosts.get(0).getLastName());
    }

    @Test
    void veryLastPostalCodeShouldEqualTo89714() {
        //ACT
        List<Host> hosts = hostFileRepository.findAll();
        //ASSERT
        int expected = 89714;
        assertEquals(expected, hosts.get(hosts.size() - 1).getPostalCode());
    }
}