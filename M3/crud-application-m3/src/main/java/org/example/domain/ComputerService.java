package org.example.domain;
import org.example.dal.DALException;
import org.example.models.Computer;
import java.util.List;

public interface ComputerService {
    ComputerResult addComputer(Computer computer) throws DALException;
    List<Computer> viewComputers() throws DALException;
    ComputerResult removeComputer(int id) throws DALException;
}
