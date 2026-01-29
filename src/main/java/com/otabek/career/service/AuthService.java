package com.otabek.career.service;


import com.otabek.career.dto.request.AuthRequest;
import com.otabek.career.dto.response.AuthResponse;
import com.otabek.career.entity.*;
import com.otabek.career.repository.*;
import com.otabek.career.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final ProfessorRepository professorRepository;
    private final IndustryRepository industryRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            if ("student123".equals(password)) {
                return generateResponse(email, "student", student.get().getFirstName());
            }
            throw new RuntimeException("Invalid Password for Student");
        }

        Optional<Staff> staff = staffRepository.findByEmail(email);
        if (staff.isPresent()) {
            if ("staff@123".equals(password)) {
                return generateResponse(email, "staff", staff.get().getFirstName());
            }
            throw new RuntimeException("Invalid Password for Staff");
        }

        Optional<Professor> prof = professorRepository.findByEmail(email);
        if (prof.isPresent()) {
            if ("professor123".equals(password)) {
                String firstName = getString(prof);
                return generateResponse(email, "professor",firstName );
            }
            throw new RuntimeException("Invalid Password for Professor");
        }

        Optional<IndustryPartner> partner = industryRepository.findByEmail(email);
        if (partner.isPresent()) {
            if ("industry123".equals(password)) {
                return generateResponse(email, "industry", partner.get().getCompanyName());
            }
            throw new RuntimeException("Invalid Password for Industry");
        }

        throw new RuntimeException("User not found with email: " + email);
    }

    private static String getString(Optional<Professor> prof) {
        Professor professor = prof.orElseThrow(() -> new RuntimeException("Professor not found"));
        Map<String, Object> data = professor.getData();
        Object personalInformation = data.get("personalInformation");
        String firstName="professor";
        if (personalInformation instanceof Map){
            Map<String, Object> personalInformationMap = (Map<String, Object>) personalInformation;
            firstName =(String) personalInformationMap.get("firstName");
        }
        return firstName;
    }

    private AuthResponse generateResponse(String email, String role, String name) {
        String token = jwtUtil.generateToken(email, role);

        return AuthResponse.builder()
                .token(token)
                .role(role)
                .name(name)
                .build();
    }
}