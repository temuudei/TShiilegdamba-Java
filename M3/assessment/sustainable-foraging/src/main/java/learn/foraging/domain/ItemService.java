package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ItemRepository;
import learn.foraging.models.Category;
import learn.foraging.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> findByCategory(Category category) {
        return repository.findAll().stream()
                         .filter(i -> i.getCategory() == category)
                         .collect(Collectors.toList());
    }

    //Updates an item
    public Result<Item> update(Item item) throws DataException {
        Result<Item> result = validate(item);
        validateIfItemIsInFile(item);
        if (!result.isSuccess()) {
            return result;
        }
        if (repository.update(item)) {
            result.setPayload(item);
        }
        return result;
    }

    //This method adds an item to the file
    public Result<Item> add(Item item) throws DataException {
        Result<Item> result = validate(item);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(repository.add(item));
        return result;
    }
    //Main verification method which calls supporting verification methods
    private Result<Item> validate(Item item) {
        Result<Item> result = validateNulls(item);
        if (!result.isSuccess()) {
            return result;
        }
        checkDuplicate(item, result);
        validatePrice(item, result);
        validateCategoryItemPrice(item, result);
        return result;
    }
    //Validates nulls
    private Result<Item> validateNulls(Item item) {
        Result<Item> result = new Result<>();
        if (item == null) {
            result.addErrorMessage("Item must not be null");
            return result;
        }

        if (item.getName() == null || item.getName().isBlank()) {
            result.addErrorMessage("Item name is required.");
        }

        if (item.getDollarPerKilogram() == null) {
            result.addErrorMessage("$/Kg is required.");
        }
        return result;
    }
    //Checking duplicates
    private void checkDuplicate(Item item, Result<Item> result) {
        if (repository.findAll().stream()
                                .anyMatch(i -> i.getName().equalsIgnoreCase(item.getName()))) {
            result.addErrorMessage(String.format("Item '%s' is a duplicate.", item.getName()));
        }
    }
    //Checking prices
    private void validatePrice(Item item, Result<Item> result) {
        if ((item.getDollarPerKilogram().compareTo(BigDecimal.ZERO) < 0
                || item.getDollarPerKilogram().compareTo(new BigDecimal("7500.00")) > 0)) {
            result.addErrorMessage("%/Kg must be between 0.00 and 7500.00.");
        }
    }
    //Checking Poison and Inedible items' prices
    private void validateCategoryItemPrice(Item item, Result<Item> result) {
        if (item.getCategory().equals(Category.valueOf("INEDIBLE")) || item.getCategory().equals(Category.valueOf("POISONOUS"))) {
            if (item.getDollarPerKilogram().compareTo(BigDecimal.ZERO) > 0) {
                result.addErrorMessage("POISONOUS and INEDIBLE categories items' prices cannot exceed $0.");
            }
        }
    }

    private void validateIfItemIsInFile(Item item) {
        Result<Item> result = new Result<>();
        List<Item> repo = repository.findAll();
        boolean isExisting = true;
        for (int i = 0; i < repo.size(); i++) {
            if (item.getId() != repo.get(i).getId()) {
                isExisting = false;
            }
        }
        if (!isExisting) {
            result.addErrorMessage(String.format("Item %s is not in the file. Cannot be updated therefore.", item.getName()));
        }
    }
}
