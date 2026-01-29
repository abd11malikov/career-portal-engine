package com.otabek.career.controller;

import com.otabek.career.entity.Staff;
import com.otabek.career.service.StaffService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<Staff>> getAll() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/by-email")
    public ResponseEntity<Staff> getByEmail(@RequestParam @Email(message = "Email should be valid") String email) {
        return ResponseEntity.ok(staffService.getStaffByEmail(email));
    }
}