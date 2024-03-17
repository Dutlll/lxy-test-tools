package www.lxy.component.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class TestReader implements ItemReader<String> {
    private int cmp = 1;
    private static final int cmpChunkSize = 100;
    public TestReader(){
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
        System.out.println("init testReader...");
    }
    public void sleep(){
        try {
//            System.out.println("read i am sleep"+Thread.currentThread());

//            TimeUnit.SECONDS.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        sleep();

        cmp++;
        if (cmp == 60){
            System.out.println("point out is 60...");
        }
        if (cmp % cmpChunkSize == 0){
            return null;
        }else {
            System.out.println("execute read..."+Thread.currentThread()+"   data:"+cmp);
            return "testStr:" + cmp;
        }

    }
}
