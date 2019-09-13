package com.kn.amqp.testapplication.core.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kn.amqp.common.model.AmqpConstants;
import com.kn.amqp.common.model.events.AmqpExchangeEvent;
import com.kn.amqp.common.model.events.BookingCreatedEvent;
import com.kn.amqp.common.model.events.BookingId;
import com.kn.amqp.testapplication.core.model.commands.CreateBookingCommand;

@Service
@Transactional
public class BookingService {

    private Logger logger = LoggerFactory.getLogger(BookingService.class);

    final ApplicationEventPublisher eventPublisher;

    @Autowired
    public BookingService(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public BookingId createBooking(final CreateBookingCommand createBookingCommand) {
        logger.info("Create booking for '{}'", createBookingCommand);
        final BookingId bookingId = BookingId.of(UUID.randomUUID().toString());
        eventPublisher.publishEvent(
                AmqpExchangeEvent.of(
                BookingCreatedEvent.of(bookingId, createBookingCommand.getConsigneeName()),
                AmqpConstants.EXCHANGE_BOOKING,
                AmqpConstants.ROUTINGKEY_BOOKING_CREATED));
        logger.debug("Published event '{}'", createBookingCommand);
        return bookingId;
    }
}
