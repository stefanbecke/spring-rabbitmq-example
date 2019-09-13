package com.kn.amqp.testapplication.monitoring.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
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
    Queue bookingQueue() {
        return new Queue(AmqpConstants.MONITORING_QUEUE_BOOKING, false);
    }

    @Bean
    TopicExchange bookingExchange() {
        return new TopicExchange(AmqpConstants.EXCHANGE_BOOKING);
    }

    @Bean
    Binding binding(Queue bookingQueue, TopicExchange bookingExchange) {
        return BindingBuilder.bind(bookingQueue).to(bookingExchange).with(AmqpConstants.ROUTINGKEY_BOOKING_CREATED);
    }

    @RabbitHandler
    public void receive(BookingCreatedEvent message) {
        logger.info("received event message: {}", message);

    }
}