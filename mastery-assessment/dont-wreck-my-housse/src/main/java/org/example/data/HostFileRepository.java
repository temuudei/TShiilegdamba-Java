package org.example.data;

import org.example.models.Host;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HostFileRepository implements HostRepository {
    private final String filePath;
    private static final String HEADER = "id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate";

    public HostFileRepository(@Value("${dataFilePath:./data/hosts.csv}") String filePath) {
        this.filePath = filePath;
    }

    //Reads data from the Host file
    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",", -1);
                if (fields.length == 10) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't
        }
        return result;
    }
    //Adds host to the file
    @Override
    public Host addHost(Host host) throws DataException {
        List<Host> all = findAll();
        host.setId(java.util.UUID.randomUUID().toString());
        all.add(host);
        writeAll(all);
        return host;
    }

    //Supporting function used for parsing the data from a file
    private Host deserialize(String[] fields) {
        Host host = new Host();
        host.setId(fields[0]);
        host.setLastName(fields[1]);
        host.setEmail(fields[2]);
        host.setPhone(fields[3]);
        host.setAddress(fields[4]);
        host.setCity(fields[5]);
        host.setState(fields[6]);
        host.setPostalCode(Integer.parseInt(fields[7]));
        host.setStandardRate(new BigDecimal(fields[8]));
        host.setWeekendRate(new BigDecimal(fields[9]));
        return host;
    }

    private void writeAll(List<Host> hostList) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Host host : hostList) {
                writer.println(serialize(host));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    private String serialize (Host host) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                host.getId(),
                host.getLastName(),
                host.getEmail(),
                host.getPhone(),
                host.getAddress(),
                host.getCity(),
                host.getState(),
                host.getPostalCode(),
                host.getStandardRate(),
                host.getWeekendRate());
    }
}
