package org.example.controller;

import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class HodgepodgeController {
    private static int sheepCount = 0;
    @GetMapping("/name")
    public String myName() {
        return "Temuudei Shiilegdamba";
    }

    @GetMapping("/current/time")
    public LocalDateTime whatTimeIsIt() {
        return LocalDateTime.now();
    }

    @GetMapping("/greet/{name}")
    public String greeting(@PathVariable String name) {
        return "Hello, " + name;
    }

    @PutMapping("/sheep")
    public int countSheep() {
        return sheepCount++;
    }
}
