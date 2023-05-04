package learning.alien.data;

import learning.alien.models.Alien;

import java.util.List;

public interface AlienRepository {
    List<Alien> findAll();
    Alien findById(int alienId);
    Alien add(Alien alien);
    boolean update(Alien alien);
    boolean deleteById(int alienId);
}
