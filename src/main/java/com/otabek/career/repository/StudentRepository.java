package com.otabek.career.repository;

import com.otabek.career.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>,
        JpaSpecificationExecutor<Student> {
    @Query("SELECT AVG(s.gpa) FROM Student s")
    Double getAverageGpa();

    @Query("SELECT s.employmentStatus, COUNT(s) FROM Student s GROUP BY s.employmentStatus")
    List<Object[]> getEmploymentStats();
}