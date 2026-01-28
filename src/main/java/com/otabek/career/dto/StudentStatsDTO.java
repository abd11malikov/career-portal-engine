package com.otabek.career.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class StudentStatsDTO {
    private long totalStudents;
    private double averageGpa;
    private Map<String, Long> employmentStats;
}