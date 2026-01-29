package com.otabek.career.controller;

import com.otabek.career.dto.IndustryPartnerDTO;
import com.otabek.career.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/industry")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    @GetMapping
    public ResponseEntity<List<IndustryPartnerDTO>> getAll() {
        return ResponseEntity.ok(industryService.getAll());
    }

    @GetMapping("/by-email")
    public ResponseEntity<IndustryPartnerDTO> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(industryService.getByEmail(email));
    }
}