package com.cdzic.zicTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * 程序运行完执行
 * run是其他线程：只有当程序运行的时候才可以运行  不能单独运行
 */
@Component
public class TerminalStartupRunner implements CommandLineRunner {
    @Value("${kafka.server.address}")
    private String kafkaServerAddress;

    @Override
    public void run(String... args) throws Exception {
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.submit(new KafkaThread(kafkaServerAddress,deviceService));
        System.out.println("程序运行完成！");
    }
}
