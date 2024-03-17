package www.lxy.component.writer;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import www.lxy.component.process.TestProceccer;

import java.util.List;
@Component
@StepScope
public class TestWriter implements ItemWriter<String>, StepExecutionListener {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println("writer::"+stepExecution.getJobParameters().getParameters().get(TestProceccer.CMP_NAME).getValue());
//        String ans = "";
//        for (String s : list){
//            ans = ans + s;
//        };
//        System.out.println("execute write..."+Thread.currentThread()+"::" + ans+"\n\n");
    }
    private StepExecution stepExecution;
    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
