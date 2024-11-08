package org.sattrack.sattracker_harvestdb.Configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String UPDATE_QUEUE = "satelliteUpdateQueue";
    public static final String INSERT_QUEUE = "satelliteInsertQueue";
    public static final String EXCHANGE_NAME = "satelliteExchange";

    public static final String UPDATE_ROUTING_KEY = "satellite.update";
    public static final String INSERT_ROUTING_KEY = "satellite.insert";

    @Bean
    public Queue updateQueue() {
        return new Queue(UPDATE_QUEUE, true);
    }

    @Bean
    public Queue insertQueue() {
        return new Queue(INSERT_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange) {
        return BindingBuilder.bind(updateQueue).to(exchange).with(UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding insertBinding(Queue insertQueue, TopicExchange exchange) {
        return BindingBuilder.bind(insertQueue).to(exchange).with(INSERT_ROUTING_KEY);
    }
}