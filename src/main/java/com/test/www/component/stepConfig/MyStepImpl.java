package com.test.www.component.stepConfig;

import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;

//@Component
//@JobScope
public class MyStepImpl implements Step {
    @Override
    public String getName() {
        return "MyStepImpl";
    }

    @Override
    public boolean isAllowStartIfComplete() {
        return true;
    }

    @Override
    public int getStartLimit() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void execute(StepExecution stepExecution) throws JobInterruptedException {
        System.out.println("testStep2 execute");
        System.out.println("testStep2 execute");
        System.out.println("testStep2 execute");
        System.out.println("testStep2 execute");
        System.out.println("testStep2 execute");
        System.out.println("testStep2 execute");
    }
}
