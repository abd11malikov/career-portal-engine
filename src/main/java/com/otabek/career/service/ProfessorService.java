package com.otabek.career.service;

import com.otabek.career.dto.response.ProfessorDTO;
import com.otabek.career.entity.Professor;
import com.otabek.career.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ProfessorDTO getProfessorById(String id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found: " + id));
        return mapToDTO(professor);
    }

    private ProfessorDTO mapToDTO(Professor entity) {
        return ProfessorDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .details(entity.getData())
                .build();
    }
}