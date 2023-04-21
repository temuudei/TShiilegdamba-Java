package org.example.domain;

import org.example.data.DataException;
import org.example.data.HostRepository;
import org.example.models.Host;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HostService {
    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public Host findByEmail(String email) {
        return repository.findAll().stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    //Adds a host
    public Result<Host> addHost(Host host) throws DataException {
        Result<Host> result = validate(host);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(repository.addHost(host));
        return result;
    }

    private Result<Host> validate(Host host) {
        Result<Host> result = validateNulls(host);
        if (!result.isSuccess()) {
            return result;
        }
        checkDuplicates(host,result);
        return result;
    }

    private Result<Host> validateNulls(Host host) {
        Result<Host> result = new Result<>();
        if (host == null) {
            return result;
        }
        if (host.getLastName() == null || host.getLastName().isBlank()) {
            result.addErrorMessage("Last name cannot be empty.");
        }
        if (host.getEmail() == null || host.getEmail().isBlank()) {
            result.addErrorMessage("Email cannot be empty.");
        }
        if (host.getPhone() == null || host.getPhone().isBlank()) {
            result.addErrorMessage("Phone number cannot be empty.");
        }
        if (host.getAddress() == null || host.getAddress().isBlank()) {
            result.addErrorMessage("Address cannot be empty");
        }
        if (host.getCity() == null || host.getCity().isBlank()) {
            result.addErrorMessage("City cannot be empty");
        }
        if (host.getState() == null || host.getState().isBlank()) {
            result.addErrorMessage("State cannot be empty");
        }
        if (host.getPostalCode() == 0) {
            result.addErrorMessage("Postal code cannot be empty");
        }
        if (host.getStandardRate().compareTo(BigDecimal.ZERO) == 0) {
            result.addErrorMessage("Standard rate cannot be empty or 0");
        }
        if (host.getWeekendRate().compareTo(BigDecimal.ZERO) == 0) {
            result.addErrorMessage("Weekend rate cannot be empty or 0");
        }
        return result;
    }
    private void checkDuplicates(Host host, Result<Host> result) {
        if (repository.findAll().stream().anyMatch(i -> i.getLastName().equalsIgnoreCase(host.getLastName()) &&
                                                        i.getEmail().equalsIgnoreCase(host.getEmail()) &&
                                                        i.getPhone().equalsIgnoreCase(host.getPhone()) &&
                                                        i.getAddress().equalsIgnoreCase(host.getAddress()) &&
                                                        i.getCity().equalsIgnoreCase(host.getCity()) &&
                                                        i.getState().equalsIgnoreCase(host.getState()) &&
                                                        i.getPostalCode() == host.getPostalCode() &&
                                                        i.getStandardRate().equals(host.getStandardRate()) &&
                                                        i.getWeekendRate().equals(host.getWeekendRate()))) {
            result.addErrorMessage(String.format("'%s, %s' already exists in our database.", host.getLastName(), host.getEmail()));
        }
    }
}
