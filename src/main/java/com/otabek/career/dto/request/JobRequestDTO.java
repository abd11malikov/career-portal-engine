package com.otabek.career.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class JobRequestDTO {
    private String id;

    @NotBlank(message = "Job title is required")
    @Size(max = 200, message = "Job title should not exceed 200 characters")
    private String title;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name should not exceed 100 characters")
    private String company;

    @NotBlank(message = "Job type is required")
    @Size(max = 20, message = "Job type should not exceed 20 characters")
    private String type;      // "internship", "full-time"

    @NotBlank(message = "Schedule is required")
    @Size(max = 20, message = "Schedule should not exceed 20 characters")
    private String schedule;  // "part-time", "remote"

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location should not exceed 200 characters")
    private String location;

    @NotNull(message = "Posted date is required")
    private LocalDate postedDate;

    @NotNull(message = "Deadline is required")
    private LocalDate deadline;

    private Map<String, Object> details;
}