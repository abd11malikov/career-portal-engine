package com.otabek.career.repository;

import com.otabek.career.entity.IndustryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IndustryRepository extends JpaRepository<IndustryPartner, String> {
    Optional<IndustryPartner> findByEmail(String email);
}