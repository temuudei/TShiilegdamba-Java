package org.example.data;

import org.example.models.Guest;

import java.util.List;

public interface GuestRepository {
    List<Guest> findAll();
    Guest addGuest(Guest guest) throws DataException;
}
