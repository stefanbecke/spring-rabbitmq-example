package com.kn.amqp.testapplication.receiver.booking.message;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kn.amqp.common.model.AmqpConstants;

@Configuration
public class BookingMessageConfig {

    static final String BOOKING_QUEUE_FOO = "c.receiver.booking.foo";

    static final String BOOKING_QUEUE_BAR = "c.receiver.booking.bar";

    @Bean
    HeadersExchange bookingExchange() {
        return new HeadersExchange(AmqpConstants.EXCHANGE_BOOKING);
    }

    @Bean
    Queue bookingQueueFoo() {
        return new Queue(BOOKING_QUEUE_FOO, true);
    }


    @Bean
    Binding bindingBosch(Queue bookingQueueFoo, HeadersExchange  bookingExchange) {
        return BindingBuilder.bind(bookingQueueFoo).to(bookingExchange).where(AmqpConstants.HEADER_CONSIGNEE).matches("foo");
    }

    @Bean
    Queue bookingQueueBar() {
        return new Queue(BOOKING_QUEUE_BAR, true);
    }

    @Bean
    Binding binding(Queue bookingQueueBar, HeadersExchange bookingExchange) {
        return BindingBuilder.bind(bookingQueueBar).to(bookingExchange).where(AmqpConstants.HEADER_CONSIGNEE).matches("bar");
    }
}
