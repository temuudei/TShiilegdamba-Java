package org.example.dal;

import org.springframework.beans.factory.annotation.Value;
import org.example.models.Computer;
import org.springframework.stereotype.Repository;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComputerFileRepository implements ComputerRepository {
    private List<Computer> computers;
    private String fileName = "./data.txt";

    public ComputerFileRepository(@Value("./data.txt")String fileName) {
        computers = new ArrayList<>();
        //this.fileName = fileName;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
        }
    }

    public List<Computer> loadComputers() throws DALException {
        List<Computer> computers = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            // When there are no more lines, readLine() return null.
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",");
                Computer computer = new Computer();
                computer.setId(Integer.parseInt(fields[0]));
                computer.setBrandName(fields[1]);
                computer.setPrice(Double.parseDouble(fields[2]));
                computer.setReleaseYear(Integer.parseInt(fields[3]));
                computer.setCpu(fields[4]);
                computer.setGpu(fields[5]);
                computer.setOperatingSystem(fields[6]);
                computers.add(computer);
            }

        } catch (IOException ex) {
            throw new DALException("Unable to read file");
        }
        return computers;
    }

    public void saveComputers(List<Computer> computer) throws DALException {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            throw new DALException("Unable to create file");
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName);
            for (Computer c : computers) {
                String line = String.format("%d,%s,%.2f,%d,%s,%s,%s", c.getId(), c.getBrandName(), c.getPrice(),
                        c.getReleaseYear(), c.getCpu(), c.getGpu(), c.getOperatingSystem());
                writer.println(line);
            }
        } catch (FileNotFoundException ex) {
            throw new DALException("Unable to write to file");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    @Override
    public Computer create(Computer computer) throws DALException {
        computers = loadComputers();
        int nextId = 0;
        for (Computer c : computers) {
            if (c.getId() > nextId) {
                nextId = c.getId();
            }
        }
        nextId++;
        computer.setId(nextId);
        computers.add(computer);
        saveComputers(computers);
        return computer;
    }

    @Override
    public List<Computer> readAll() throws DALException {
        computers = loadComputers();
        return computers;
    }

    @Override
    public Computer readById(int id) throws DALException {
        computers = loadComputers();
        for (Computer c : computers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Computer computer) throws DALException {
        computers = loadComputers();
        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).getId() == id) {
                computers.set(i, computer);
                break;
            }
        }
        saveComputers(computers);
    }

    @Override
    public void delete(int id) throws DALException {
        computers = loadComputers();
        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).getId() == id) {
                computers.remove(i);
            }
        }
        saveComputers(computers);
    }
}
