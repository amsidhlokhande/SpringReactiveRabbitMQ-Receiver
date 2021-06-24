package com.amsidh.mvc.springreactiverabbitmqreceiver;

import com.amsidh.mvc.springreactiverabbitmqreceiver.consume.QueueConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class SpringReactiveRabbitMqConsumerApplication implements CommandLineRunner {
    private final QueueConsumer queueConsumer;

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveRabbitMqConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        queueConsumer.consumeMessage();
    }
}
