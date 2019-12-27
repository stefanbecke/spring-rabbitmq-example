package com.kn.amqp.testapplication.receiver.booking.message;

import static com.kn.amqp.testapplication.receiver.booking.message.BookingMessageConfig.BOOKING_QUEUE_FOO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kn.amqp.common.model.events.BookingCreatedEvent;

@Component
@RabbitListener(queues = BOOKING_QUEUE_FOO)
public class BookingMessageFooConsumer {

    private static final Logger logger = LoggerFactory.getLogger(BookingMessageFooConsumer.class);

    @RabbitHandler
    public void receive(BookingCreatedEvent message) {
        logger.info("received booking created for foo message: {}", message);

    }
}