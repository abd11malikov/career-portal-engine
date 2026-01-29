package com.otabek.career.controller;

import com.otabek.career.dto.request.JobRequestDTO;
import com.otabek.career.dto.response.JobDTO;
import com.otabek.career.service.JobService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAll() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getById(@PathVariable @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid job ID format") String id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('INDUSTRY')")
    public ResponseEntity<JobDTO> create(@Valid @RequestBody JobRequestDTO dto) {
        return ResponseEntity.ok(jobService.createJobFromRequest(dto));
    }
}