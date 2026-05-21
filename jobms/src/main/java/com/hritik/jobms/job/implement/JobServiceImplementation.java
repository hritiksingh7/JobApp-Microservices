package com.hritik.jobms.job.implement;

// import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.hritik.jobms.job.Job;
import com.hritik.jobms.job.JobRepository;
import com.hritik.jobms.job.JobService;

@Service 
public class JobServiceImplementation implements JobService {

    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll(){
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job){
        jobRepository.save(job);
    }

    @Override
    public Job findById(Long Id){
        return jobRepository.findById(Id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long Id){
        try {
            if(jobRepository.existsById(Id)){
                jobRepository.deleteById(Id);
                return true;
            }
        } catch (Exception e) {
            // if job to be deleted is not found
            return false;
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long Id, Job updatedJob){
        Optional<Job> jobOptional = jobRepository.findById(Id);

        if(jobOptional.isPresent()){
            Job job = jobOptional.get();

            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setTitle(updatedJob.getTitle());
            job.setCompanyId(updatedJob.getCompanyId());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
    
    // commenting out this because it was done using manual handling, now we are switching to using JPA repostiroy for all the tasks

    // private List<Job> jobs = new ArrayList<>();
    // private Long nextId = 1L;

    // @Override
    // public List<Job> findAll(){
    //     return jobs;
    // }

    // @Override
    // public void createJob(Job job){
    //     job.setId(nextId++);
    //     jobs.add(job);
    // }

    // @Override
    // public Job findById(Long Id){
    //     for(Job job: jobs){
    //         if(job.getId().equals(Id)){
    //             return job;
    //         }
    //     }
    //     return null;
    // }

    // @Override
    // public boolean deleteJobById(Long Id){
    //     Iterator<Job> it = jobs.iterator();

    //     while(it.hasNext()){
    //         Job job = it.next();
    //         if(job.getId().equals(Id)){
    //             it.remove();
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // @Override
    // public boolean updateJobById(Long Id, Job updatedJob){
    //     for(Job job : jobs){
    //         if(job.getId().equals(Id)){
    //             job.setDescription(updatedJob.getDescription());
    //              job.setLocation(updatedJob.getDescription());
    //             job.setMaxSalary(updatedJob.getMaxSalary());
    //             job.setMinSalary(updatedJob.getMinSalary());
    //             job.setTitle(updatedJob.getTitle());
    //             return true;
    //         }
    //     }
    //     return false;
    // }
}


