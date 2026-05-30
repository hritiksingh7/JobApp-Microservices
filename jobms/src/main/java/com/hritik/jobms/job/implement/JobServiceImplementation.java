package com.hritik.jobms.job.implement;

import java.util.ArrayList;
// import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hritik.jobms.job.external.Company;
import com.hritik.jobms.job.external.Review;
import com.hritik.jobms.job.mapper.JobMapper;
import com.hritik.jobms.job.Job;
import com.hritik.jobms.job.JobRepository;
import com.hritik.jobms.job.JobService;
import com.hritik.jobms.job.dto.JobDTO;


@Service 
public class JobServiceImplementation implements JobService {

    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;
    

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAll(){

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOs = new ArrayList<>();


        for(Job job: jobs){
            JobDTO jobDTO = convertToJobDTO(job);
            jobDTOs.add(jobDTO);
        }

        return jobDTOs;
    }

    @Override
    public void createJob(Job job){
        jobRepository.save(job);
    }

    @Override
    public JobDTO findById(Long Id){
        Job job = jobRepository.findById(Id).orElse(null);

        if(job != null){
            return convertToJobDTO(job);
        }

        return null;
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

    // helper methods
    public JobDTO convertToJobDTO(Job job){

        Company company = restTemplate.getForObject(
            "http://COMPANY-SERVICE/companies/" + job.getCompanyId(),
            Company.class
        );

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
            "http://REVIEW-SERVICE/reviews?companyId=" + job.getCompanyId(), 
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Review>>() {
            }
        );

        List<Review> reviews = reviewResponse.getBody();

        JobDTO jobDTO = JobMapper.mapToJobDTO(job, company, reviews);
        return jobDTO;
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


