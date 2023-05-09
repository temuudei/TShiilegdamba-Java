package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.*;

public class Phone {

    private int phoneId;
    @NotBlank(message =  "Phone name cannot be empty.")
    private String phoneName;
    @NotBlank(message = "Operating system cannot be null.")
    private String phoneOS;
    @NotBlank(message = "Release date cannot be null.")
    @PastOrPresent(message = "Release date cannot be in the future.")
    private LocalDate releaseDate;
    @NotNull(message = "Phone price cannot be null.")
    private BigDecimal phonePrice;
    private String isStillInGoodCondition;
}
