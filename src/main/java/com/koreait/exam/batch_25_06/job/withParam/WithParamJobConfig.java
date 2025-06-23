package com.koreait.exam.batch_25_06.job.withParam;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WithParamJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job WithParamJob(Step WithParamStep1) {
        return jobBuilderFactory.get("withParamJob")
//                .incrementer(new RunIdIncrementer()) // 강제로 매번 다른 ID를 실행할 때 파라미터로 부여
                .start(WithParamStep1())
                .build();
    }

    @Bean
    @JobScope
    public Step WithParamStep1() {
        return stepBuilderFactory
                .get("WithParamStep1")
                .tasklet(WithParamStep1Tasklet())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet WithParamStep1Tasklet(
            @Value("#{jobParameters['name']}") String name,
            @Value("#{jobParameters['age']}") String age) {
        return (stepContribution, chunkContext) -> {
            System.out.println("withParam 111111111111111!!!");
            System.out.printf("name: %s  age: %s", name, age);
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    @StepScope
    public Tasklet WithParamStep1Tasklet() {
        return (stepContribution, chunkContext) -> {
            System.out.println("withParam 111111111111111!!!");
            return RepeatStatus.FINISHED;
        };
    }

}