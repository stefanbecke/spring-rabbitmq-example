package com.kn.amqp.testapplication.sender.booking.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.kn.amqp.common.model.AmqpConstants;
import com.kn.amqp.common.model.events.BookingCreatedEvent;

@Component
public class BookingExchangeSender {

	private Logger logger = LoggerFactory.getLogger(BookingExchangeSender.class);

	private AmqpTemplate amqpTemplate;

	@Autowired
	public BookingExchangeSender(final AmqpTemplate amqpTemplate ) {
		this.amqpTemplate = amqpTemplate;
	}

	@EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async("messagePublishExecutor")
	public void send(final BookingCreatedEvent event) {
		amqpTemplate.convertAndSend( AmqpConstants.EXCHANGE_BOOKING,
                AmqpConstants.ROUTINGKEY_BOOKING_CREATED,
                event,
                new BookingMessagePostProcessor(event.getConsigneeName()));
        logger.info("Published event '{}' to exchange '{}' ", event, AmqpConstants.EXCHANGE_BOOKING);
	}
}