package com.test.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableBatchProcessing
@MapperScan("com.test.www")

public class BatchMain {
    List<User> args = new
            ArrayList<>();
    public User getUser(){
        return new User();
    }
    public class User{
        String name = "aaa";
        int old = 18;
        public void exeAdd(){
            args.add(this);
        }
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", old=" + old +
                    '}';
        }
    }
    /**
     * 学习spring batch相关参数
     * 理清公司项目架构
     * 深入学习相关源码
     * 文章完成
     * @param args
     */
    public static void main(String[] args) {
//        final Semaphore semaphore = new Semaphore(10);
////        final BatchMain batchMain = new BatchMain();
////        final User user = batchMain.getUser();
////        final BatchMain batchMain1 = new BatchMain();
////        user.exeAdd();
////        System.out.println(batchMain.args);
////        System.out.println(batchMain1.args);
        SpringApplication.run(BatchMain.class, args);
    }
}