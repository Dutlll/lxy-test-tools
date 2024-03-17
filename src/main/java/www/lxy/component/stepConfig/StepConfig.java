package www.lxy.component.stepConfig;

import www.lxy.component.partition.TestParitiion;
import www.lxy.component.process.TestProceccer;
import www.lxy.component.reader.TestReader;
import www.lxy.component.writer.TestWriter;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class StepConfig {
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    private static final String TEST_STEP = "test_step";
    @Resource
    private TestReader testReader;
    @Resource
    private TestProceccer testProceccer;
    @Resource
    private TestWriter testWriter;

    @Bean
    public Step getTestStep() {

        System.out.println("start one job and ready to build testStep...");
        System.out.println("start one job and ready to build testStep...");
        System.out.println("start one job and ready to build testStep...");

        final int threadNum = 1;

        Executor threadPoolExecutor = new ThreadPoolExecutor(threadNum,threadNum,3, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(100));
        final ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor(threadPoolExecutor);

        return stepBuilderFactory.get(TEST_STEP)
                .<String ,String>chunk(1)
                .reader(testReader)
                .processor(testProceccer)
                .listener(new SkipListener(){
                    @Override
                    public void onSkipInRead(Throwable t) {
                        System.out.println("error process appear...");
                        System.out.println("1");
                    }

                    @Override
                    public void onSkipInWrite(Object item, Throwable t) {
                        System.out.println("error process appear...");
                        System.out.println("2");
                    }

                    @Override
                    public void onSkipInProcess(Object item, Throwable t) {
                        System.out.println("error process appear...");
                        System.out.println("3");
                    }
                })
                .writer(testWriter)
                .listener(new SkipListener(){
                    @Override
                    public void onSkipInRead(Throwable t) {
                        System.out.println("error appear...");
                        System.out.println("1");
                    }

                    @Override
                    public void onSkipInWrite(Object item, Throwable t) {
                        System.out.println("error appear...");
                        System.out.println("2");
                    }

                    @Override
                    public void onSkipInProcess(Object item, Throwable t) {
                        System.out.println("error appear...");
                        System.out.println("3");
                    }
                })
//                .taskExecutor(concurrentTaskExecutor)
                .build();
    }

    //文件分区处理器-处理分区
    @Bean
    public PartitionHandler userPartitionHandler() {
        final int coreThreadNum = 6;
        Executor threadPoolExecutor = new ThreadPoolExecutor(coreThreadNum, coreThreadNum, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));

        ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor(threadPoolExecutor);

        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(7633295);
//        handler.setTaskExecutor(concurrentTaskExecutor);
        handler.setStep(getTestStep());
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }

    @Autowired
    private TestParitiion testParitiion;

    //主分区操作步骤
    @Bean
    @JobScope
    public Step testMasterStep() {

        final int coreThreadNum = 6;
        Executor threadPoolExecutor = new ThreadPoolExecutor(coreThreadNum, coreThreadNum, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));

        ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor(threadPoolExecutor);

        return stepBuilderFactory.get("masterStep")
                .partitioner("partition_StepName",new TestParitiion())
                .partitionHandler(userPartitionHandler())
                .taskExecutor(concurrentTaskExecutor)
                .build();
    }

//    @Bean
//    @JobScope
//    public Step getTestPartitionStep() {
//        System.out.println("start one job and ready to build testStep...");
//        System.out.println("start one job and ready to build testStep...");
//        System.out.println("start one job and ready to build testStep...");
//        Executor threadPoolExecutor = new ThreadPoolExecutor(3,4,3, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(100));
//        final ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor(threadPoolExecutor);
//        return stepBuilderFactory.get(TEST_STEP)
//                .partitioner("testPartitionStep",testParitiion).build();
//
//    }
    private static final String TEST_PARTITION_STEP = "test_partition_step";
    private static final String TEST_PARTITION_NAME = "test_partition_step";
    @Resource
    private StepConfig stepConfig;
//    @Bean
//    @JobScope
//    public Step getTestPartitionStep(){
//        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
//        handler.setTaskExecutor(new SyncTaskExecutor());
//        handler.setGridSize(3);
//        handler.setStep(stepConfig.getTestStep());
//        return stepBuilderFactory.get(TEST_PARTITION_STEP)
//                .partitioner(TEST_PARTITION_NAME,testParitiion)
//                .partitionHandler(handler)
//                .splitter()
//                .aggregator()
//                .build();
//    }
}