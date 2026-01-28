package com.otabek.career.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otabek.career.entity.Professor;
import com.otabek.career.entity.Staff;
import com.otabek.career.entity.Student;
import com.otabek.career.repository.ProfessorRepository;
import com.otabek.career.repository.StaffRepository;
import com.otabek.career.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;
    private final StaffRepository staffRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public void run(String... args) throws Exception {
        if (studentRepository.count() == 0) {

            try {
                InputStream inputStream = TypeReference.class.getResourceAsStream("/data/students.json");

                List<Map<String, Object>> jsonList = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>(){});

                System.out.println(" Found " + jsonList.size() + " students in JSON.");

                List<Student> students = jsonList.stream()
                        .map(this::mapJsonToEntity)
                        .toList();

                studentRepository.saveAll(students);

                System.out.println(" Successfully saved " + students.size() + " students to Postgres!");

            } catch (Exception e) {
                System.err.println(" Seeding Failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (staffRepository.count() == 0) {
            try {
                InputStream inputStream = TypeReference.class.getResourceAsStream("/data/staff.json");
                List<Map<String, Object>> jsonList = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>(){});

                List<Staff> staffList = jsonList.stream()
                        .map(json -> {
                            Staff s = new Staff();
                            s.setId((String) json.get("id"));
                            s.setFirstName((String) json.get("firstName"));
                            s.setLastName((String) json.get("lastName"));
                            s.setEmail((String) json.get("email"));
                            s.setData(json); // Dump the rest
                            return s;
                        })
                        .toList();

                staffRepository.saveAll(staffList);
                System.out.println("Saved " + staffList.size() + " staff members.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (professorRepository.count() == 0) {
            System.out.println("Seeding Professors...");
            InputStream is = TypeReference.class.getResourceAsStream("/data/professors.json");
            List<Map<String, Object>> jsonList = objectMapper.readValue(is, new TypeReference<List<Map<String, Object>>>(){});

            List<Professor> professors = jsonList.stream()
                    .map(json -> {
                        Professor p = new Professor();
                        Object accountObj = json.get("account");
                        if (accountObj instanceof Map) {
                            Map<String, Object> accountMap = (Map<String, Object>) accountObj;

                            String email = (String) accountMap.get("email");
                            String id = (String) accountMap.get("id");
                            p.setEmail(email);
                            p.setId(id);

                        } else {
                            System.out.println("Warning: Professor " + p.getId() + " has no account/email data.");
                        }

                        p.setData(json);

                        return p;
                    })
                    .toList();

            professorRepository.saveAll(professors);
            System.out.println("Saved " + professors.size() + " professors.");
        }
    }

    private Student mapJsonToEntity(Map<String, Object> json) {
        Student s = new Student();

        s.setId((String) json.get("id"));
        s.setFirstName((String) json.get("firstName"));
        s.setLastName((String) json.get("lastName"));
        s.setEmail((String) json.get("email"));
        s.setStudentId((String) json.get("studentId"));
        s.setMajor((String) json.get("major"));
        s.setEmploymentStatus((String) json.get("employmentStatus"));

        Object gpaObj = json.get("gpa");
        if (gpaObj instanceof Number) {
            s.setGpa(((Number) gpaObj).doubleValue());
        }

        Object needsUpdateObj = json.get("needsUpdate");
        if (needsUpdateObj != null) {
            s.setNeedsUpdate(Boolean.valueOf(needsUpdateObj.toString()));
        }

        s.setData(json);

        return s;
    }

}