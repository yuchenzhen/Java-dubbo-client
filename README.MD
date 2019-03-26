### 服务消费者Consumer

1.0 使用Spring Boot 建立一个Spring 消费者的微服务 

1.1 定义接口，这里的接口路径位置和包名最好一致 （不然要自己调整）

```java
// TestProviderService.java
package com.dubbo.learn.dubbo;

public interface TestProviderService {
    String Hello (String who);
}
```



1.2 定义一个 Service调用该dubbo 接口

```java
// TestConsumerService.java
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
```

在主程序函数调用该Service 的函数

```java
// DubboConsumerApplication.java
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
```



@Reference`这个注解就是用来调用 dubbo 对应的接口的。所以也是要引入跟服务端的那几个包

```xml
<!--pom.xml-->
<dependency>
  <groupId>com.alibaba.boot</groupId>
  <artifactId>dubbo-spring-boot-starter</artifactId> 
  <version>0.2.1.RELEASE</version>
</dependency>
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>dubbo</artifactId>
  <version>2.6.5</version>
</dependency>
<dependency>
  <groupId>org.apache.curator</groupId>
  <artifactId>curator-framework</artifactId>
  <version>2.11.1</version>
</dependency>
```

1.4 最后我们把dubbo 的启动配置写到`application.properties`里面

```properties
#application.properties
server.port=8830
dubbo.application.name=consumer
#注册中心地址
dubbo.registry.address=zookeeper://127.0.0.1:2181

dubbo.scan.base-packages=com.dubboo.learn.dubbo
dubbo.protocol.port=20880
```

项目目录结构如下：

![](http://ww1.sinaimg.cn/large/6026faa6gy1g1fgt0ik3aj20aa0irdgx.jpg)



启动后效果如下

![](http://ww1.sinaimg.cn/large/6026faa6gy1g1fgfyr094j211l0e60yc.jpg)
