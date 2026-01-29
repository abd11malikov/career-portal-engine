package com.otabek.career.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @Column(name = "id")
    private String id;

    private String title;

    private String company;

    // Using String instead of Enums for easier migration
    private String type;      // "internship", "full-time"
    private String schedule;  // "part-time", "remote"

    private String location;

    private LocalDate postedDate;
    private LocalDate deadline;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> data;
}