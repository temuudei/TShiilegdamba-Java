package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                         .filter(i -> i.getLastName().startsWith(prefix))
                         .collect(Collectors.toList());
    }
    //Adds forager to the file
    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(repository.add(forager));
        return result;
    }

    //Main verification method that calls supporting methods
    private Result<Forager> validate(Forager forager) {
        Result<Forager> result = validateNulls(forager);
        if (!result.isSuccess()) {
            return result;
        }
        checkDuplicate(forager, result);
        return result;
    }
    //Checking nulls
    private Result<Forager> validateNulls(Forager forager) {
        Result<Forager> result = new Result<>();
        if (forager == null) {
            result.addErrorMessage("Forager must not be null");
            return result;
        }
        if (forager.getFirstName() == null || forager.getFirstName().isBlank()) {
            result.addErrorMessage("Forager's first name is required");
        }
        if (forager.getLastName() == null || forager.getLastName().isBlank()) {
            result.addErrorMessage("Forager's last name name is required");
        }
        if (forager.getState() == null || forager.getState().isBlank()) {
            result.addErrorMessage("Forager's state is required");
        }
        return result;
    }
    //Checking duplicates
    private void checkDuplicate(Forager forager, Result<Forager> result) {
        if (repository.findAll().stream()
                .anyMatch(f -> f.getFirstName().equalsIgnoreCase(forager.getFirstName())
                            && f.getLastName().equalsIgnoreCase(forager.getLastName())
                            && f.getState().equalsIgnoreCase(forager.getState()))) {
            result.addErrorMessage("" + forager.getFirstName() + " "
                                      + forager.getLastName() + ", "
                                      + forager.getState() + " is already in the file. No duplicates are allowed.");
        }
    }
}
