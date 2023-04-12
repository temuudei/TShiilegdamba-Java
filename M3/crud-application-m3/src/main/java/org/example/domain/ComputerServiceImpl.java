package org.example.domain;

import org.example.dal.ComputerRepository;
import org.example.dal.DALException;
import org.example.models.Computer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService{

    private ComputerRepository computerRepository;

    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public ComputerResult addComputer(Computer computer) throws DALException {
        ComputerResult result = new ComputerResult();
        if(computer == null){
            result.addMessage("An computer is required");
            return result;
        }
        if(computer.getBrandName().isBlank()){
            result.addMessage("computer brand name is required");
        }
        if(computer.getPrice() <= 0){
            result.addMessage("computers must have a price greater than zero");
        }
        if (computer.getCpu().isEmpty()) {
            result.addMessage("computer cpu name is required");
        }
        if (computer.getGpu().isEmpty()) {
            result.addMessage("computer gpu name is required");
        }
        if (computer.getOperatingSystem().isEmpty()) {
            result.addMessage("computer operating system name is required");
        }
        if (computer.getReleaseYear() == 0) {
            result.addMessage("computer release date is required");
        }

        if(result.isSuccessful()){
            computer = computerRepository.create(computer);
            result.setComputer(computer);
        }
        return result;
    }

    @Override
    public List<Computer> viewComputers() throws DALException {
        return computerRepository.readAll();
    }

    @Override
    public ComputerResult removeComputer(int id) throws DALException {
        ComputerResult result = new ComputerResult();
        computerRepository.delete(id);
        return result;
    }
}
