package com.kn.amqp.common.model.gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.MessageConversionException;

/**
 * Provided a custon Gson2JsonMessage Converter, because spring supports only
 * by default Jackson2JsonMessageConverter.
 */
public class Gson2JsonMessageConverter extends AbstractJsonMessageConverter {

    private static ClassMapper classMapper = new DefaultClassMapper();

    private static AmqpGsonBuilder gson = AmqpGsonBuilder.INSTANCE;

    public Gson2JsonMessageConverter() {
        super();
    }

    @Override
    protected Message createMessage(Object object, MessageProperties messageProperties) {
        byte[] bytes;
        try {
            String jsonString = gson.toJson(object);
            bytes = jsonString.getBytes(getDefaultCharset());
        } catch (IOException e) {
            throw new MessageConversionException("Failed to convert Message content", e);
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(getDefaultCharset());
        if (bytes != null) {
            messageProperties.setContentLength(bytes.length);
        }
        classMapper.fromClass(object.getClass(), messageProperties);

        return new Message(bytes, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        Object content = null;
        MessageProperties properties = message.getMessageProperties();
        if (properties != null) {
            String contentType = properties.getContentType();
            if (contentType != null && contentType.contains("json")) {
                String encoding = properties.getContentEncoding();
                if (encoding == null) {
                    encoding = getDefaultCharset();
                }
                try {
                   content = convertBytesToObject(message.getBody(), encoding, Class.forName( properties.getHeaders().get("__TypeId__").toString()));
                } catch (Exception e) {
                    throw new MessageConversionException("Failed to convert Message content", e);
                }
            } else {
                throw new MessageConversionException("Could not convert incoming message with content-type [" + contentType + "]");
            }
        }
        if (content == null) {
            content = message.getBody();
        }
        return content;
    }

    private Object convertBytesToObject(byte[] body, String encoding, Class<?> clazz)
            throws UnsupportedEncodingException {
        String contentAsString = new String(body, encoding);
        return gson.fromJson(contentAsString, clazz);
    }
}