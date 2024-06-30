package com.lgcns.cloudxper.optdashbatch.batch.job;

import com.lgcns.cloudxper.optdashbatch.batch.tasklet.PowerScheduleTasklet;
import com.lgcns.cloudxper.optdashbatch.batch.tasklet.PowerScheduleValidationCheckTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PowerSchedule {

    private static final String JOB_NAME = "powerScheduleJob";
    private static final String STEP_NAME = "PowerScheduleStep";

    @Bean
    public Job powerScheduleJob(JobRepository jobRepository, Step powerScheduleStep, Step powerScheduleValidationStep) {
        System.out.println("powerScheduleManagementJob");
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(powerScheduleStep)
                .next(powerScheduleValidationStep)
                .build();
    }

    @Bean
    public Step powerScheduleStep(JobRepository jobRepository, Tasklet powerScheduleTasklet, PlatformTransactionManager transactionManager) {
        System.out.println("powerScheduleManagementStep");

        return new StepBuilder(STEP_NAME, jobRepository)
                .tasklet(powerScheduleTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step powerScheduleValidationStep(JobRepository jobRepository, Tasklet powerScheduleTasklet, PlatformTransactionManager transactionManager) {
        System.out.println("powerScheduleValidationStep");
        return new StepBuilder(STEP_NAME, jobRepository)
                .tasklet(powerScheduleTasklet, transactionManager)
                .build();
    }
    @Bean
    public Tasklet powerScheduleTasklet() {
        System.out.println("powerScheduleTasklet");
        return new PowerScheduleTasklet();
    }

    @Bean
    public Tasklet powerScheduleValidationCheckTasklet(){
        System.out.println("powerScheduleValidationCheckTasklet");
        return new PowerScheduleValidationCheckTasklet();
    }
}