package org.example.dal;

import org.example.models.Computer;

import java.util.List;

public interface ComputerRepository {
    Computer create(Computer computer) throws DALException;
    List<Computer> readAll() throws DALException;
    Computer readById(int id) throws DALException;
    void update(int id, Computer computer) throws DALException;
    void delete(int id) throws DALException;
}
