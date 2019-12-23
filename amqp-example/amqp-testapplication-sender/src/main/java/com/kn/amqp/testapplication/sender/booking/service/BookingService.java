package com.kn.amqp.testapplication.sender.booking.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kn.amqp.common.model.events.BookingCreatedEvent;
import com.kn.amqp.common.model.events.BookingId;
import com.kn.amqp.testapplication.sender.booking.command.CreateBookingCommand;

@Service
@Transactional
public class BookingService {

    private Logger logger = LoggerFactory.getLogger(BookingService.class);

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public BookingService(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public BookingId createBooking(final CreateBookingCommand createBookingCommand) {
        logger.info("Create booking for '{}'", createBookingCommand);
        final BookingId bookingId = BookingId.of(UUID.randomUUID().toString());
        eventPublisher.publishEvent(BookingCreatedEvent.of(bookingId, createBookingCommand.getConsigneeName()));
        logger.debug("Published event '{}'", createBookingCommand);
        return bookingId;
    }
}
