package com.otabek.career.repository;

import com.otabek.career.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findByTitleContainingIgnoreCase(String title);
}