package com.otabek.career.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
@Table(name = "professors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @Column(name = "id")
    private String id;

    @Column(unique = true)
    private String email;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> data;
}