package learning.alien.controller;

import learning.alien.data.AlienRepository;
import learning.alien.models.Alien;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aliens")
public class AlienController {
    private AlienRepository alienRepository;
    public AlienController(AlienRepository alienRepository) {
        this.alienRepository = alienRepository;
    }

    @GetMapping
    public List<Alien> findAll() {
        return alienRepository.findAll();
    }
}
