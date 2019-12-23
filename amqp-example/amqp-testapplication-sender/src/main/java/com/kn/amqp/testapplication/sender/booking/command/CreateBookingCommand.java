package com.kn.amqp.testapplication.sender.booking.command;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import com.kn.amqp.common.model.core.ImmutablesStyle;
import com.kn.amqp.common.model.gson.AmqpGsonBuilder;
import com.kn.amqp.common.model.gson.ToJson;

@Value.Immutable
@ImmutablesStyle.DeepImmutables
@Gson.TypeAdapters
public interface CreateBookingCommand extends ToJson {


    static CreateBookingCommand of(final String consigneeName) {
        return ImmutableCreateBookingCommand.of(consigneeName);
    }

    static CreateBookingCommand fromJson(final String json) {
        return AmqpGsonBuilder.INSTANCE.fromJson(json, CreateBookingCommand.class);
    }

    @Value.Parameter
    String getConsigneeName();

}
