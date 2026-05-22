package com.hritik.jobms.job;

import java.util.List;

import com.hritik.jobms.job.dto.JobWithCompanyDTO;

public interface JobService {

    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job findById(Long Id);
    boolean deleteJobById(Long Id);
    boolean updateJobById(Long Id, Job job);
}


