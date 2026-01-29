package com.otabek.career.controller;

import com.otabek.career.dto.response.StudentStatsDTO;
import com.otabek.career.entity.Student;
import com.otabek.career.service.StudentService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String major,
            @RequestParam(required = false) String employmentStatus,
            @RequestParam(required = false) Double minGpa,
            @RequestParam(required = false) Boolean needsUpdate,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int limit
    ) {
        return ResponseEntity.ok(studentService.getStudents(search, major, employmentStatus, minGpa, needsUpdate, page, limit));
    }
    @GetMapping("/stats")
    public ResponseEntity<StudentStatsDTO> getStats() {
        return ResponseEntity.ok(studentService.getStats());
    }

    @GetMapping("/by-email")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid student ID format") String id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
