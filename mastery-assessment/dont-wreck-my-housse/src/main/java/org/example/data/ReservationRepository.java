package org.example.data;

import org.example.models.Host;
import org.example.models.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findById(Host host);

    Reservation add(Reservation reservation) throws DataException;

    boolean update(Reservation reservation) throws DataException;

    boolean delete(Reservation reservation) throws DataException;
}
