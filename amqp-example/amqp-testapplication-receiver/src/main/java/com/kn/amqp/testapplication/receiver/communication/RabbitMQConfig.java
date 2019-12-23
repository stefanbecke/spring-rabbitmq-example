package com.kn.amqp.testapplication.receiver.communication;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kn.amqp.common.model.AmqpConstants;
import com.kn.amqp.common.model.gson.Gson2JsonMessageConverter;

@Configuration
public class RabbitMQConfig {

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
    
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Gson2JsonMessageConverter();
    }
}
