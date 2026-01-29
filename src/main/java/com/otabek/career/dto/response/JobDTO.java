package com.otabek.career.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
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