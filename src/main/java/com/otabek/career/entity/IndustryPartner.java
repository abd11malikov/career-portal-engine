package com.otabek.career.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "industry_partners")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustryPartner {

    @Id
    @Column(name = "id")
    private String id;

    private String companyName;
    private String contactName;

    @Column(unique = true)
    private String email;

    @ElementCollection
    @CollectionTable(name = "industry_focus_areas", joinColumns = @JoinColumn(name = "industry_id"))
    @Column(name = "focus_area")
    private List<String> focusAreas;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> data;
}