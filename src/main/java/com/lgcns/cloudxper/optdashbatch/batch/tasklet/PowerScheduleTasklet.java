package com.lgcns.cloudxper.optdashbatch.batch.tasklet;

import com.lgcns.cloudxper.optdashbatch.domain.apiclient.PowerScheduleApiClient;
import com.lgcns.cloudxper.optdashbatch.domain.service.OptDashBoardService;
import com.lgcns.cloudxper.optdashbatch.domain.transformer.PowerScheduleTransformer;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class PowerScheduleTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        PowerScheduleApiClient.powerScheduleApi();
        PowerScheduleTransformer.transform();
        OptDashBoardService.addData();
        return RepeatStatus.FINISHED;
    }
}

