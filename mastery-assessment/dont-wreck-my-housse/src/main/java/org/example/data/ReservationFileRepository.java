package org.example.data;

import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationFileRepository implements ReservationRepository {
    private static final String HEADER = "id,start_date,end_date,first_name,last_name,email,phone,total_price";
    private final String filepath;

    public ReservationFileRepository(@Value("${dataFilePath:./data/reservations}") String filepath) {
        this.filepath = filepath;
    }

    //Read file by the host info
    @Override
    public List<Reservation> findById(Host host) {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(host)))) {
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",", -1);
                if (fields.length == 8) {
                    result.add(deserialize(fields, host));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    //Adds information to the file
    @Override
    public void add(Reservation reservation) throws DataException {
        List<Reservation> all = findById(reservation.getHost());
        int nextId = getNextId(all);
        reservation.setId(nextId);
        all.add(reservation);
        writeAll(all, reservation.getHost());
    }

    //Updates information in the file
    @Override
    public boolean update(Reservation reservation) throws DataException {
        List<Reservation> all = findById(reservation.getHost());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == reservation.getId()) {
                all.set(i, reservation);
                writeAll(all, reservation.getHost());
                return true;
            }
        }
        return false;
    }

    //Deletes a line of code in the file
    @Override
    public boolean delete(Reservation reservation) throws DataException {
        List<Reservation> all = findById(reservation.getHost());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == reservation.getId()) {
                all.remove(i);
                writeAll(all, reservation.getHost());
                return true;
            }
        }
        return false;
    }

    //Writes data to the file
    private void writeAll(List<Reservation> reservations, Host host) throws DataException {
        try (PrintWriter writer = new PrintWriter(getFilePath(host))) {
            writer.println(HEADER);
            for (Reservation reservation : reservations) {
                writer.println(serialize(reservation));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    //Assigns next id
    private int getNextId(List<Reservation> reservations) {
        int maxId = 0;
        for (Reservation reservation : reservations) {
            if (maxId < reservation.getId()) {
                maxId = reservation.getId();
            }
        }
        return maxId + 1;
    }

    private Reservation deserialize(String[] fields, Host host) {
        Reservation reservation = new Reservation();
        reservation.setId(Integer.parseInt(fields[0]));
        reservation.setStartDate(LocalDate.parse(fields[1]));
        reservation.setEndDate(LocalDate.parse(fields[2]));

        Guest guest = new Guest();
        guest.setFirstName(fields[3]);
        guest.setLastName(fields[4]);
        guest.setEmail(fields[5]);
        guest.setPhone(fields[6]);
        guest.setTotal(new BigDecimal(fields[7]));
        reservation.setGuest(guest);

        Host host1 = new Host();
        host1.setId(host.getId());
        reservation.setHost(host1);
        return reservation;
    }

    //Converts string and adds it in to the file
    private String serialize(Reservation reservation) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                reservation.getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuest().getFirstName(),
                reservation.getGuest().getLastName(),
                reservation.getGuest().getEmail(),
                reservation.getGuest().getPhone(),
                reservation.getGuest().getTotal());
    }

    //Gets filepath
    private String getFilePath(Host host) {
        return Paths.get(filepath, host.getId() + ".csv").toString();
    }
}
