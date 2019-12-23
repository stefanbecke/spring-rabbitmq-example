package com.kn.amqp.testapplication.sender.communication.amqp;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kn.amqp.common.model.AmqpConstants;
import com.kn.amqp.common.model.gson.Gson2JsonMessageConverter;

@Configuration
public class RabbitMQConfig {

	@Bean
	Exchange bookingExchange() {
		return new TopicExchange(AmqpConstants.EXCHANGE_BOOKING);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Gson2JsonMessageConverter();
	}

}
