package com.adidas.services.inventory.inventoryWorker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StudenttSideController {


    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping("/runXml")
    public BatchStatus run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters params = new JobParameters();
        final JobExecution run = jobLauncher.run(job, params);
        System.out.println(run.getStatus().toString());
        while (run.isRunning()){
            System.out.println("....");
        }
        return run.getStatus();
    }
}
