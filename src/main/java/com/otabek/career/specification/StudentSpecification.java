package com.otabek.career.specification;

import com.otabek.career.entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class StudentSpecification {

    public static Specification<Student> getStudents(String search, String major, String employmentStatus, Double minGpa, Boolean needsUpdate) {
        return (root, query, criteriaBuilder) -> {
            Specification<Student> spec = Specification.where(null);

            // 1. Search (First Name OR Last Name OR Email)
            if (StringUtils.hasText(search)) {
                String searchPattern = "%" + search.toLowerCase() + "%";
                Specification<Student> searchSpec = (r, q, cb) -> cb.or(
                        cb.like(cb.lower(r.get("firstName")), searchPattern),
                        cb.like(cb.lower(r.get("lastName")), searchPattern),
                        cb.like(cb.lower(r.get("email")), searchPattern)
                );
                spec = spec.and(searchSpec);
            }

            // 2. Filter by Major
            if (StringUtils.hasText(major)) {
                spec = spec.and((r, q, cb) -> cb.equal(r.get("major"), major));
            }

            // 3. Filter by Employment Status
            if (StringUtils.hasText(employmentStatus)) {
                spec = spec.and((r, q, cb) -> cb.equal(r.get("employmentStatus"), employmentStatus));
            }

            // 4. Filter by GPA (Greater than or Equal)
            if (minGpa != null) {
                spec = spec.and((r, q, cb) -> cb.greaterThanOrEqualTo(r.get("gpa"), minGpa));
            }

            // 5. Needs Update
            if (needsUpdate != null) {
                spec = spec.and((r, q, cb) -> cb.equal(r.get("needsUpdate"), needsUpdate));
            }

            return spec.toPredicate(root, query, criteriaBuilder);
        };
    }
}