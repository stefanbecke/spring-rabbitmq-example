package com.kn.amqp.common.model.events;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import com.kn.amqp.common.model.core.IdSupplier;
import com.kn.amqp.common.model.core.ImmutablesStyle;
import com.kn.amqp.common.model.gson.ToJson;

@Value.Immutable
@ImmutablesStyle.Default
@Gson.TypeAdapters
public interface BookingId extends IdSupplier, ToJson {

    static BookingId of(final String id) {
        return ImmutableBookingId.of(id);
    }
}
