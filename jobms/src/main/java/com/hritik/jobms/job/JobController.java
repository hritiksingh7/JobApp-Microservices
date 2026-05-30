package com.hritik.jobms.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RestController;

import com.hritik.jobms.job.dto.JobDTO;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        List<JobDTO> jobs = jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    } 

    @GetMapping("/{Id}")
    public ResponseEntity<JobDTO> findById(@PathVariable Long Id){
        JobDTO job = jobService.findById(Id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long Id){
        boolean deleted = jobService.deleteJobById(Id);
        if(deleted){
            return ResponseEntity.ok("Job Deleted Successfully");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long Id, @RequestBody Job job){
        boolean updated = jobService.updateJobById(Id, job);
        if(updated){
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error: Job not updated", HttpStatus.NOT_FOUND);
    }
    
}
