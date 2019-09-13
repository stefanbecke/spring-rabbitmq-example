package com.kn.amqp.testapplication.core.communication.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class RabbitMqEventExecutorConfig {


    @Bean
    public TaskExecutor rabbitMqExecutor(final RabbitMqRejectedExecutionHandler rabbitMqRejectedExecutionHandler) {
    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("RabbitMqExecutor-");
        executor.setRejectedExecutionHandler(rabbitMqRejectedExecutionHandler);
        return executor;
    }

}
