package com.example.exercisevaalidationq3.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EventSystem {

    @NotNull(message = "cannot be null")
    @Min(2)
    private  int ID;

    @NotEmpty(message = "cannot be null")
    @Size(min = 15)
    private String description;

    @NotNull(message = "cannot be null")
    @Min(25)
    private int capacity;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

}
