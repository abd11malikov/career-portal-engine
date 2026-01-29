package com.otabek.career.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class IndustryPartnerDTO {
    private String id;
    private String companyName;
    private String contactName;
    private String email;
    private List<String> focusAreas;
    private Map<String, Object> details;
}