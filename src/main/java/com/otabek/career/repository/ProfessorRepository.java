package com.otabek.career.repository;

import com.otabek.career.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {
    Optional<Professor> findByEmail(String email);
}