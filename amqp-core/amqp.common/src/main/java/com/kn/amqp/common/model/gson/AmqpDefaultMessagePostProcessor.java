package com.kn.amqp.common.model.gson;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class AmqpDefaultMessagePostProcessor implements MessagePostProcessor {

    private static final String UUID_HEADER  = "uuid";

    @Override
    public Message postProcessMessage(final Message message) throws AmqpException {
        final String uuid = UUID.randomUUID().toString();
        message.getMessageProperties().setTimestamp(new Date());
        message.getMessageProperties().setMessageId(uuid);
        message.getMessageProperties().setHeader(UUID_HEADER, uuid);
        return message;
    }
}
