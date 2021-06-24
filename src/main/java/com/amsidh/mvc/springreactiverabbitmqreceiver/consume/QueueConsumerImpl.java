package com.amsidh.mvc.springreactiverabbitmqreceiver.consume;

import com.amsidh.mvc.springreactiverabbitmqreceiver.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.rabbitmq.Receiver;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Slf4j
@Service
public class QueueConsumerImpl implements QueueConsumer {

    private final Receiver receiver;

    @Override
    public void consumeMessage() {
        AtomicInteger atomicInteger = new AtomicInteger();
        receiver.consumeAutoAck(AppConfig.QUEUE_NAME).log("Share consumed")
                .subscribe(delivery -> {
                    log.info("Share # {} - {}", atomicInteger.incrementAndGet(), new String(delivery.getBody()));
                }, throwable -> log.error("Error consuming share", throwable), () -> log.info("All shares are consumed"));
    }
}
