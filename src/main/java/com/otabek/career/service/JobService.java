package com.otabek.career.service;

import com.otabek.career.dto.JobDTO;
import com.otabek.career.entity.Job;
import com.otabek.career.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public JobDTO getJobById(String id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found: " + id));
        return mapToDTO(job);
    }

    public JobDTO createJob(JobDTO dto) {
        Job job = new Job();
        job.setId(dto.getId() != null ? dto.getId() : "job-" + UUID.randomUUID().toString().substring(0,8));

        job.setTitle(dto.getTitle());
        job.setCompany(dto.getCompany());
        job.setType(dto.getType());
        job.setSchedule(dto.getSchedule());
        job.setLocation(dto.getLocation());
        job.setPostedDate(LocalDate.now());
        job.setDeadline(dto.getDeadline());
        job.setData(dto.getDetails());

        Job saved = jobRepository.save(job);
        return mapToDTO(saved);
    }

    private JobDTO mapToDTO(Job job) {
        return JobDTO.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .type(job.getType())
                .schedule(job.getSchedule())
                .location(job.getLocation())
                .postedDate(job.getPostedDate())
                .deadline(job.getDeadline())
                .details(job.getData())
                .build();
    }
}