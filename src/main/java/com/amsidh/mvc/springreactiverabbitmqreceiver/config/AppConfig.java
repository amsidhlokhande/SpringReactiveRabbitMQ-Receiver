package com.amsidh.mvc.springreactiverabbitmqreceiver.config;

import com.rabbitmq.client.Connection;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class AppConfig {

    public static final String QUEUE_NAME = "share";

    @Autowired
    private Mono<Connection> connectionMono;

    @Bean
    Mono<Connection> getRabbitMQConnection(CachingConnectionFactory cachingConnectionFactory) {
        return Mono.fromCallable(() -> cachingConnectionFactory.getRabbitConnectionFactory().newConnection());
    }

    @Bean
    Receiver getReceiver(Mono<Connection> monoConnection) {
        return RabbitFlux.createReceiver(new ReceiverOptions().connectionMono(monoConnection));
    }

    @PreDestroy
    public void close() throws IOException {
        Objects.requireNonNull(this.connectionMono.block()).close();
    }
}
