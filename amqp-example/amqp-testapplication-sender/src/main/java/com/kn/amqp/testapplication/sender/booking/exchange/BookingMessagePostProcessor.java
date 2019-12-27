package com.kn.amqp.testapplication.sender.booking.exchange;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;

import com.kn.amqp.common.model.AmqpConstants;
import com.kn.amqp.common.model.gson.AmqpDefaultMessagePostProcessor;

public class BookingMessagePostProcessor extends AmqpDefaultMessagePostProcessor {


    private String consigneeHeader;

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        super.postProcessMessage(message);
        if (consigneeHeader != null) {
            message.getMessageProperties().setHeader(AmqpConstants.HEADER_CONSIGNEE, consigneeHeader.toLowerCase());
        }
        return message;
    }

    public BookingMessagePostProcessor(final String consigneeHeader) {
        this.consigneeHeader = consigneeHeader;
    }
}
