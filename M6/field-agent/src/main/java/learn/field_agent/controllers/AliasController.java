package learn.field_agent.controllers;

import learn.field_agent.domain.AliasService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Alias;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/alias")
public class AliasController {
    private final AliasService service;

    public AliasController(AliasService service) {
        this.service = service;
    }

    @GetMapping
    public List<Alias> findAll() {
        return service.findAll();
    }

    @GetMapping("/{alias_id}")
    public ResponseEntity<Alias> findById(@PathVariable int alias_id) {
        Alias alias = service.findById(alias_id);
        if (alias == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(alias);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Alias alias) {
        Result<Alias> result = service.add(alias);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{alias_id}")
    public ResponseEntity<Object> update(@PathVariable int alias_id, @RequestBody Alias alias) {
        if (alias_id != alias.getAlias_id()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Alias> result = service.update(alias);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{alias_id}")
    public ResponseEntity<Void> deleteById(@PathVariable int alias_id) {
        if (service.deleteById(alias_id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
