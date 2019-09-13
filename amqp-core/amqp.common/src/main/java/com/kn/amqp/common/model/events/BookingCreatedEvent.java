package com.kn.amqp.common.model.events;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import com.kn.amqp.common.model.core.ImmutablesStyle;
import com.kn.amqp.common.model.gson.AmqpGsonBuilder;

@Value.Immutable
@ImmutablesStyle.DeepImmutables
@Gson.TypeAdapters
public interface BookingCreatedEvent extends Event {


    static BookingCreatedEvent of(final BookingId bookingId, final String consigneeName) {
        return ImmutableBookingCreatedEvent.of(bookingId, consigneeName);
    }

    static BookingCreatedEvent fromJson(final String json) {
        return AmqpGsonBuilder.INSTANCE.fromJson(json, BookingCreatedEvent.class);
    }

    @Value.Parameter
    BookingId getBookingId();

    @Value.Parameter
    String getConsigneeName();

}
