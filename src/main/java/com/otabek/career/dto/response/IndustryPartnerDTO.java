package com.otabek.career.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndustryPartnerDTO {
    private String id;
    private String companyName;
    private String contactName;
    private String email;
    private List<String> focusAreas;
    private Map<String, Object> details;
}