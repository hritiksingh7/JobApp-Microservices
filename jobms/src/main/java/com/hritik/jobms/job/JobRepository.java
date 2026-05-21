package com.hritik.jobms.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> { // <Job, Long> defines the entity type and the Primary key type needed for this interface

    
}
