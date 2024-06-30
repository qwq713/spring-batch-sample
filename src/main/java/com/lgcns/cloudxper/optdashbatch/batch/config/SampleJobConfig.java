package com.lgcns.cloudxper.optdashbatch.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class SampleJobConfig{

    @Bean
    public Tasklet myTasklet() {
        System.out.println("myTasklet");
        return new MyTasklet();
    }

    @Bean
    public Step myStep(JobRepository jobRepository, Tasklet myTasklet, PlatformTransactionManager transactionManager) {
        System.out.println("myStep");
        return new StepBuilder("myStep", jobRepository)
                .tasklet(myTasklet, transactionManager)
                .build();
    }

    @Bean
    public Job sampleJob(JobRepository jobRepository, Step myStep) {
        System.out.println("sampleJob");
        return new JobBuilder("sampleJob", jobRepository)
                .start(myStep)
                .build();
    }
}
class MyTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 여기에 실제 작업을 구현합니다.
        System.out.println("MyTasklet is executing");

        // 예를 들어, 어떤 비즈니스 로직을 수행할 수 있습니다.
        performBusinessLogic();

        // 작업이 완료되면 FINISHED를 반환합니다.
        return RepeatStatus.FINISHED;
    }

    private void performBusinessLogic() {
        // 실제 비즈니스 로직을 여기에 구현합니다.
        System.out.println("Performing business logic in MyTasklet");
        // 예: 데이터 처리, 외부 서비스 호출 등
    }
}


