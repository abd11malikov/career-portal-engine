package com.otabek.career.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class JobDTO {
    private String id;
    private String title;
    private String company;
    private String type;
    private String schedule;
    private String location;
    private LocalDate postedDate;
    private LocalDate deadline;
    private Map<String, Object> details;
}