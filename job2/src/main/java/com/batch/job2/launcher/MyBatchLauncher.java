package com.batch.job2.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyBatchLauncher {
    private final Job importOrderJob;

    private final JobLauncher jobLauncher;

    public MyBatchLauncher(@Qualifier("importUserJob") Job importOrderJob,
                           @Qualifier("myJobLauncher") JobLauncher jobLauncher) {
        this.importOrderJob = importOrderJob;
        this.jobLauncher = jobLauncher;
    }

    public void runBatchJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("importUserJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(importOrderJob, jobParameters);
    }

}