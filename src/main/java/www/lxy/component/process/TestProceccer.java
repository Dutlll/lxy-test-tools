package www.lxy.component.process;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@StepScope
public class TestProceccer implements ItemProcessor<String, String>, StepExecutionListener {
    public void sleep(){
        try {
            System.out.println("process i am sleep"+Thread.currentThread());
            int a = 1/0;
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String process(String s) throws Exception {
        final JobParameter jobParameter1 = this.stepExecution.getJobParameters().getParameters().get(CMP_NAME);
        if (jobParameter1 == null){
            System.out.println("null...");
            JobParameter jobParameter = new JobParameter(0L,false);
            stepExecution.getJobParameters().getParameters().put(CMP_NAME, jobParameter);
            System.out.println("null fixed...");
        }
        long value = (Long)this.stepExecution.getJobParameters().getParameters().get(CMP_NAME).getValue();
        value++;
        JobParameter jobParameter = new JobParameter(value,false);
        stepExecution.getJobParameters().getParameters().put(CMP_NAME, jobParameter);
        return s + " processed Value";
    }
    StepExecution stepExecution;
    private long cmp = 0;
    public static final String CMP_NAME = "cmp_name";
    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameter jobParameter = new JobParameter(cmp,false);
        stepExecution.getJobParameters().getParameters().put(CMP_NAME, jobParameter);
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
