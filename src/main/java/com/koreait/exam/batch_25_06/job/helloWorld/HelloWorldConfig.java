package com.koreait.exam.batch_25_06.job.helloWorld;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class HelloWorldConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // job : 여러개의 step들로 구성

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob") // helloWorldJob: Job 이름
                .incrementer(new RunIdIncrementer()) // 강제로 매번 다른 ID를 부여 -> 파라미터
                .start(helloWorldStep1())
                .build();
    }

    @Bean
    public Step helloWorldStep1() {
        return stepBuilderFactory.get("helloWorldStep1").tasklet(helloWorldTasklet()).build(); // helloWorldStep1: Step 이름
    }

    @Bean
    public Tasklet helloWorldTasklet() {
        return (contribution, chunkContext) -> {// 메서드 종료
            System.out.println("헬로월드!!!");
            return RepeatStatus.FINISHED; // 끝났다.
        };
    }
}