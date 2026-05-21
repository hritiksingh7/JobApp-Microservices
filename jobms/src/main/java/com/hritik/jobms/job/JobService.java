package com.hritik.jobms.job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createJob(Job job);
    Job findById(Long Id);
    boolean deleteJobById(Long Id);
    boolean updateJobById(Long Id, Job job);
}


