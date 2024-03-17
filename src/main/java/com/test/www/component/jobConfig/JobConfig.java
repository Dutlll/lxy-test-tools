package com.test.www.component.jobConfig;

import com.test.www.component.stepConfig.StepConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.UUID;

@Configuration
public class JobConfig implements InitializingBean {
    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepConfig stepConfig;
//    @Resource
//    private MyStepImpl testStep2;
//    @Bean
//    @JobScope
    public Job getTestJob(){
        return jobBuilderFactory
                .get("testJob112312999"+ UUID.randomUUID())
                .start(stepConfig.getTestStep())
//                .start(new MyStepImpl())
//                .start(stepConfig.getTestPartition
//                Step())
                .incrementer(new RunIdIncrementer())
                .build();
    }



    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private JobConfig jobConfig;
    @Override
    public void afterPropertiesSet() throws Exception {
//        try{
//            jobLauncher.run(jobConfig.getTestJob(),new JobParameters());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        final ThreadPoolExecutor threadPoolExecutor
//                = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(100));
//        threadPoolExecutor.submit(()->{
//            try{
//                jobLauncher.run(jobConfig.getTestJob(),new JobParameters());
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        });
    }
}
