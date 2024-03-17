package com.test.www.component.partition;

import com.test.www.utils.GlobalPrint;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@StepScope
public class TestParitiion implements Partitioner, GlobalPrint {
    @Override
    public Map<String, ExecutionContext> partition(int i) {
        globalPrint(() -> {
            System.out.println();
            System.out.println(TestParitiion.class.getSimpleName());
            System.out.println("receive a var grade size::" + i);
            System.out.println();
        });
        final HashMap<String, ExecutionContext> hashMap = new HashMap<>();
        ExecutionContext executionContext1 = new ExecutionContext();
        ExecutionContext executionContext2 = new ExecutionContext();
        ExecutionContext executionContext3 = new ExecutionContext();
        ExecutionContext executionContext6 = new ExecutionContext();

        hashMap.put("execute-line-1-key",executionContext1);
        hashMap.put("execute-line-2-key",executionContext2);
        hashMap.put("execute-line-3-key",executionContext3);
        hashMap.put("execute-line-6-key",executionContext6);
        return hashMap;
    }

}
