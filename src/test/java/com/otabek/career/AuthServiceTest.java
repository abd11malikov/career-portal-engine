package com.otabek.career;

import com.otabek.career.dto.request.AuthRequest;
import com.otabek.career.dto.response.AuthResponse;
import com.otabek.career.entity.Student;
import com.otabek.career.repository.IndustryRepository;
import com.otabek.career.repository.ProfessorRepository;
import com.otabek.career.repository.StaffRepository;
import com.otabek.career.repository.StudentRepository;
import com.otabek.career.security.JwtUtil;
import com.otabek.career.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock private StudentRepository studentRepository;
    @Mock private StaffRepository staffRepository;
    @Mock private ProfessorRepository professorRepository;
    @Mock private IndustryRepository industryRepository;
    @Mock private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    void login_Success_Student() {
        AuthRequest request = new AuthRequest();
        request.setEmail("student@test.com");
        request.setPassword("student123");

        Student mockStudent = new Student();
        mockStudent.setEmail("student@test.com");
        mockStudent.setFirstName("Otabek");

        when(studentRepository.findByEmail("student@test.com")).thenReturn(Optional.of(mockStudent));
        when(jwtUtil.generateToken("student@test.com", "student")).thenReturn("fake-jwt-token");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("fake-jwt-token", response.getToken());
        assertEquals("student", response.getRole());
        assertEquals("Otabek", response.getName());
    }

    @Test
    void login_Fail_WrongPassword() {
        AuthRequest request = new AuthRequest();
        request.setEmail("student@test.com");
        request.setPassword("WRONG_PASS");

        Student mockStudent = new Student();
        mockStudent.setEmail("student@test.com");

        when(studentRepository.findByEmail("student@test.com")).thenReturn(Optional.of(mockStudent));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertEquals("Invalid Password for Student", ex.getMessage());
    }

    @Test
    void login_Fail_UserNotFound() {
        AuthRequest request = new AuthRequest();
        request.setEmail("ghost@test.com");
        request.setPassword("any");

        when(studentRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(staffRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(professorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(industryRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertTrue(ex.getMessage().contains("User not found"));
    }
}