package com.dubbo.learn;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.learn.dubbo.TestProviderService;
import org.springframework.stereotype.Component;

@Component
public class TestConsumerService {
    @Reference(version = "1.0.0")
    TestProviderService testProviderService;

    public void consumer (String who) {
        String res = testProviderService.Hello(who);
        System.out.println("consumer : provider says " + res);
    }
}
