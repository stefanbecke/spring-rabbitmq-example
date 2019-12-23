package com.kn.amqp.common.model.gson;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class AmqpDefaultMessagePostProcessor implements MessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setTimestamp(new Date());
        message.getMessageProperties().setMessageId(UUID.randomUUID().toString());
        return message;
    }
}
