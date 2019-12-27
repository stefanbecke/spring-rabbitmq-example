package com.kn.amqp.common.model;

public class AmqpConstants {

    public static final String EXCHANGE_BOOKING = "e.core.booking";

    public static final String ROUTINGKEY_BOOKING_ALL = "booking.*";
    public static final String ROUTINGKEY_BOOKING_CREATED = "booking.created";
    public static final String ROUTINGKEY_BOOKING_UPDATED = "booking.updated";
    public static final String ROUTINGKEY_BOOKING_DELETED = "booking.deleted";

    public static final String HEADER_CONSIGNEE = "consignee";


}
