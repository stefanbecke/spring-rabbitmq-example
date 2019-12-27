package com.kn.amqp.testapplication.receiver.communication;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kn.amqp.common.model.gson.Gson2JsonMessageConverter;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Gson2JsonMessageConverter();
    }
}
