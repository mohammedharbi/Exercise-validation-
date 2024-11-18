package com.example.exercisevaalidationq2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectTracker {

    @NotNull(message = "Cannot be null")
    @Min(2)
    private int ID;

    @NotEmpty(message ="Cannot be null")
    @Size(min = 8)
    private String title;

    @NotEmpty(message = "cannot be null")
    @Size(min = 15)
    private String description;

    @NotEmpty(message = "cannot be null")
    @Pattern(regexp = "^(Not Started|in Progress|Completed)$")
    private String status;

    @NotEmpty(message = "cannot be null")
    @Size(min = 6)
    private String companyName;


}
