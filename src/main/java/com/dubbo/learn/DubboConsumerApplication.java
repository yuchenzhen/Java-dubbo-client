package com.dubbo.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.dubbo.learn.TestConsumerService;

@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DubboConsumerApplication.class, args);
        TestConsumerService testConsumer = run.getBean(TestConsumerService.class);
        testConsumer.consumer("White");
    }

}
