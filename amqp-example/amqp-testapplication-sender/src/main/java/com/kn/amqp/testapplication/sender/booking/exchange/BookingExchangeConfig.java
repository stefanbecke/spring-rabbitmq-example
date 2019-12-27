package com.kn.amqp.testapplication.sender.booking.exchange;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kn.amqp.common.model.AmqpConstants;

@Configuration
public class BookingExchangeConfig {

    @Bean
    Exchange bookingExchange() {
        return new HeadersExchange(AmqpConstants.EXCHANGE_BOOKING);
    }
}
