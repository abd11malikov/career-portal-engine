package com.otabek.career.service;

import com.otabek.career.dto.StudentStatsDTO;
import com.otabek.career.entity.Student;
import com.otabek.career.repository.StudentRepository;
import com.otabek.career.specification.StudentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Page<Student> getStudents(String search,
                                     String major,
                                     String employmentStatus,
                                     Double minGpa,
                                     Boolean needsUpdate,
                                     int page,
                                     int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return studentRepository.findAll(
                StudentSpecification.getStudents(search, major, employmentStatus, minGpa, needsUpdate),
                pageable
        );
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findOne(
                (root, query, cb) -> cb.equal(root.get("email"), email)
        ).orElseThrow(() -> new RuntimeException("Student not found with email: " + email));
    }

    public StudentStatsDTO getStats() {
        // 1. Total Count
        long total = studentRepository.count();

        // 2. Average GPA (Handle null if DB is empty)
        Double avgGpa = studentRepository.getAverageGpa();
        if (avgGpa == null) avgGpa = 0.0;

        // 3. Employment Map (Convert List<Object[]> to Map)
        List<Object[]> rawStats = studentRepository.getEmploymentStats();
        Map<String, Long> employmentMap = new HashMap<>();

        for (Object[] row : rawStats) {
            String status = (String) row[0]; // "employed"
            Long count = (Long) row[1];      // 500
            if (status != null) {
                employmentMap.put(status, count);
            }
        }

        return StudentStatsDTO.builder()
                .totalStudents(total)
                .averageGpa(avgGpa)
                .employmentStats(employmentMap)
                .build();
    }
}