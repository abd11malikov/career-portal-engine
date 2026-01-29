package com.otabek.career.controller;

import com.otabek.career.dto.response.ProfessorDTO;
import com.otabek.career.service.ProfessorService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAll() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid professor ID format") String id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }
}