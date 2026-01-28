package com.otabek.career.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class ProfessorDTO {
    private String id;
    private String email;
    private Map<String, Object> details;
}