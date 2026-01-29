package com.otabek.career.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentStatsDTO {
    private long totalStudents;
    private double averageGpa;
    private Map<String, Long> employmentStats;
}