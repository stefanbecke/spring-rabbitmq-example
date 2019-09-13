package com.kn.amqp.testapplication.core.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.kn.amqp.common.model.events.AmqpExchangeEvent;

@Component
public class RabbitMQEventPublisher {

	private Logger logger = LoggerFactory.getLogger(RabbitMQEventPublisher.class);

	private AmqpTemplate amqpTemplate;

	@Autowired
	public RabbitMQEventPublisher(final AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	@EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async("rabbitMqExecutor")
	public void sendEvent(final AmqpExchangeEvent event) {
		amqpTemplate.convertAndSend(event.getExchangeName(), event.getRoutingKey(), event.getEventBody());
        logger.info("Published event '{}' to exchange '{}' with routingKey '{}'", event.getEventBody(), event.getExchangeName(), event.getRoutingKey());
	}
}