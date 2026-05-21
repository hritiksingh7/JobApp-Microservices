package com.hritik.reviewms.reviews;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByCompanyId(Long companyId);
    Optional<Review> findByIdAndCompanyId(Long Id, Long companyId);
} 
