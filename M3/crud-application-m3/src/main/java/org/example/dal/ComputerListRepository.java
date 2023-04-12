package org.example.dal;

import org.example.models.Computer;

import java.util.List;

public class ComputerListRepository implements ComputerRepository {
    private List<Computer> computers;

    public ComputerListRepository(List<Computer> computers) {
        this.computers = computers;
    }
    @Override
    public Computer create(Computer computer) throws DALException {
        int nextId = 0;
        for (Computer c : computers) {
            if (c.getId() > nextId) {
                nextId = c.getId();
            }
        }
        nextId++;
        computer.setId(nextId);
        computers.add(computer);
        return computer;
    }

    @Override
    public List<Computer> readAll() throws DALException {
        return computers;
    }

    @Override
    public Computer readById(int id) throws DALException {
        for (Computer c : computers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Computer computer) throws DALException {
        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).getId() == id) {
                computers.set(i, computer);
                break;
            }
        }
    }

    @Override
    public void delete(int id) throws DALException {
        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).getId() == id) {
                computers.remove(i);
            }
        }
    }
}
