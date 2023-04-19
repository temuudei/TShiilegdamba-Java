package org.example.data;

import org.example.models.Host;

import java.util.List;

public interface HostRepository {
    List<Host> findAll();
}
