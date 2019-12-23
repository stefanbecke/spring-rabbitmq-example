package com.kn.amqp.testapplication.receiver.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.kn.amqp.common.model.AmqpConstants;
import com.kn.amqp.common.model.events.BookingCreatedEvent;

@Component
@RabbitListener(queues = AmqpConstants.MONITORING_QUEUE_BOOKING)
public class RabbitMQEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQEventConsumer.class);

    @Bean
    TopicExchange bookingExchange() {
        return new TopicExchange(AmqpConstants.EXCHANGE_BOOKING);
    }

    @RabbitHandler
    public void receive(BookingCreatedEvent message) {
        logger.info("received event message: {}", message);

    }
}