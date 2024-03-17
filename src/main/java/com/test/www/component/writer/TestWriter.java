package com.test.www.component.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@StepScope
public class TestWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        String ans = "";
        for (String s : list){
            ans = ans + s;
        };
        System.out.println("execute write..."+Thread.currentThread()+"::" + ans+"\n\n");
    }
}
