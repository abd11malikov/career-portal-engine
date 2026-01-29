package com.otabek.career.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String role; // "student", "staff", "industry", "professor"
    private String name; // Useful for frontend "Welcome ELON"
}