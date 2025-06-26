package com.koreait.exam.batch_25_06.job.productBackup;

import com.koreait.exam.batch_25_06.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ProductBackupJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job productBackupJob() {
        return jobBuilderFactory.get("productBackupJob") // helloWorldJob: Job 이름
                .incrementer(new RunIdIncrementer()) // 강제로 매번 다른 ID를 부여 -> 파라미터
                .start(productBackupStep())
                .build();
    }

    @Bean
    @JobScope
    public Step productBackupStep() {
        return null;
    }

    // 정산데이터 job 실행 전 테스트 잡 만들기
    // job -> step -> itemReader + itemProcessor + itemWriter
//    @Bean
//    public Step sendMailStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("sendMailStep", jobRepository)
//                .<Member, Long>chunk(10, transactionManager)
//                .reader(itemReader())
//                .process(itemProcessor())
//                .writer(itemWriter())
//                .build();
//    }
}
