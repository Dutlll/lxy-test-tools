package www.lxy.component.process;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@StepScope
public class TestProceccer implements ItemProcessor<String, String> {
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
        sleep();
        System.out.println("execute process..."+Thread.currentThread()+"::"+s);
        return s + " processed Value";
    }
}
