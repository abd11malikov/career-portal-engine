package com.otabek.career.service;

import com.otabek.career.dto.response.IndustryPartnerDTO;
import com.otabek.career.entity.IndustryPartner;
import com.otabek.career.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndustryService {

    private final IndustryRepository industryRepository;

    public List<IndustryPartnerDTO> getAll() {
        return industryRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public IndustryPartnerDTO getByEmail(String email) {
        IndustryPartner partner = industryRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Industry Partner not found: " + email));
        return mapToDTO(partner);
    }

    private IndustryPartnerDTO mapToDTO(IndustryPartner entity) {
        return IndustryPartnerDTO.builder()
                .id(entity.getId())
                .companyName(entity.getCompanyName())
                .contactName(entity.getContactName())
                .email(entity.getEmail())
                .focusAreas(entity.getFocusAreas())
                .details(entity.getData())
                .build();
    }
}