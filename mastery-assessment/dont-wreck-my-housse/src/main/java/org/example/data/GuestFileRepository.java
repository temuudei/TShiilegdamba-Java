package org.example.data;

import org.example.models.Guest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestFileRepository implements GuestRepository {
    private final String filePath;
    private static final String HEADER = "guest_id,first_name,last_name,email,phone,state";
    public GuestFileRepository(@Value("${filePath:./data/guests.csv}") String filePath) {
        this.filePath = filePath;
    }

    //Reads data from the Guest file
    @Override
    public List<Guest> findAll() {
        ArrayList<Guest> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // read header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",", -1);
                if (fields.length == 6) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }
    //Adds guest to the file
    @Override
    public Guest addGuest(Guest guest) throws DataException {
        List<Guest> all = findAll();
        int nextID = getNextID(all);
        guest.setGuestID(nextID);
        all.add(guest);
        writeAll(all);
        return guest;
    }

    //Supporting function used for parsing the data from a file
    private Guest deserialize(String[] fields) {
        Guest guest = new Guest();
        guest.setGuestID(Integer.parseInt(fields[0]));
        guest.setFirstName(fields[1]);
        guest.setLastName(fields[2]);
        guest.setEmail(fields[3]);
        guest.setPhone(fields[4]);
        guest.setState(fields[5]);
        return guest;
    }

    //Assigns next ID number
    private int getNextID(List<Guest> guestList) {
        int maxID = 0;
        for (Guest guest : guestList) {
            if (maxID < guest.getGuestID()) {
                maxID = guest.getGuestID();
            }
        }
        return maxID + 1;
    }

    //Writes guest to the file
    private void writeAll(List<Guest> guestList) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Guest guest : guestList) {
                writer.println(serialize(guest));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    private String serialize(Guest guest) {
        return String.format("%s,%s,%s,%s,%s,%s",
                guest.getGuestID(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getEmail(),
                guest.getPhone(),
                guest.getState());
    }
}
