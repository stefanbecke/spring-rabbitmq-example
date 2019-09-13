package com.kn.amqp.testapplication.core.communication.async;

import static java.lang.String.valueOf;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.EMPTY;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqRejectedExecutionHandler implements RejectedExecutionHandler {

    private Logger logger = LoggerFactory.getLogger(RabbitMqRejectedExecutionHandler.class);

    @Override
    public void rejectedExecution(final Runnable runnable, final ThreadPoolExecutor threadPoolExecutor) {
        logger.error("Rejected execution for {} at {}{}",
                getSimpleClassname(runnable),
                getSimpleClassname(threadPoolExecutor),
                ofNullable(valueOf(threadPoolExecutor))
                        .map(toString -> toString.substring(toString.indexOf('[')))
                        .orElse(EMPTY));
    }

    private String getSimpleClassname(final Object object) {
        return ofNullable(object).map(Object::getClass).map(Class::getSimpleName).orElse(EMPTY);
    }
}
