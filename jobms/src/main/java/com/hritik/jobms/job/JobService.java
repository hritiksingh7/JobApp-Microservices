package com.hritik.jobms.job;

import java.util.List;

import com.hritik.jobms.job.dto.JobDTO;

public interface JobService {

    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO findById(Long Id);
    boolean deleteJobById(Long Id);
    boolean updateJobById(Long Id, Job job);
}


