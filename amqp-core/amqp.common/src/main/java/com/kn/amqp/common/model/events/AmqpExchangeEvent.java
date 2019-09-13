package com.kn.amqp.common.model.events;

import org.immutables.value.Value;

import com.kn.amqp.common.model.core.ImmutablesStyle;

@Value.Immutable
@ImmutablesStyle.DeepImmutables
public interface AmqpExchangeEvent {

    static AmqpExchangeEvent of(final Event event, final String exchangeName, final String routingKey) {
        return ImmutableAmqpExchangeEvent.of(event, exchangeName, routingKey);
    }

    @Value.Parameter
    Event getEventBody();

    @Value.Parameter
    String getExchangeName();

    @Value.Parameter
    String getRoutingKey();
}
