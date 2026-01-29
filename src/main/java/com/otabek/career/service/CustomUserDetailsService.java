package com.otabek.career.service;

import com.otabek.career.entity.*;
import com.otabek.career.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final ProfessorRepository professorRepository;
    private final IndustryRepository industryRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            return buildUserDetails(student.get().getEmail(), "student123", "ROLE_STUDENT");
        }

        Optional<Staff> staff = staffRepository.findByEmail(email);
        if (staff.isPresent()) {
            return buildUserDetails(staff.get().getEmail(), "staff@123", "ROLE_STAFF");
        }

        Optional<Professor> prof = professorRepository.findByEmail(email);
        if (prof.isPresent()) {
            return buildUserDetails(prof.get().getEmail(), "professor123", "ROLE_PROFESSOR");
        }

        Optional<IndustryPartner> partner = industryRepository.findByEmail(email);
        if (partner.isPresent()) {
            return buildUserDetails(partner.get().getEmail(), "industry123", "ROLE_INDUSTRY");
        }

        throw new UsernameNotFoundException("User not found: " + email);
    }

    private UserDetails buildUserDetails(String email, String password, String role) {
        return User.builder()
                .username(email)
                .password(password)
                .roles(role.replace("ROLE_",""))
                .build();
    }
}