package org.sattrack.sattracker_harvestdb.Configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "satelliteExchange";

    public static final String SATELLITE_QUEUE = "satelliteQueue";
    public static final String SATELLITE_GROUP_QUEUE = "satelliteGroupQueue";
    public static final String SATELLITE_UPDATE_QUEUE = "satelliteUpdateQueue";
    public static final String SATELLITE_INSERT_QUEUE = "satelliteInsertQueue";

    public static final String SATELLITE_ROUTING_KEY = "satellite";
    public static final String SATELLITE_UPDATE_ROUTING_KEY = "satelliteUpdate";
    public static final String SATELLITE_INSERT_ROUTING_KEY = "satelliteInsert";
    public static final String GROUP_UPDATE_ROUTING_KEY = "satelliteGroup";


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }


    @Bean
    public Queue satelliteQueue() {
        return new Queue(SATELLITE_QUEUE, true);
    }
    @Bean
    public Queue satelliteUpdateQueue() {
        return new Queue(SATELLITE_UPDATE_QUEUE, true);
    }

    @Bean
    public Queue satelliteInsertQueue() {
        return new Queue(SATELLITE_INSERT_QUEUE, true);
    }

    @Bean
    public Queue groupUpdateQueue() {
        return new Queue(SATELLITE_GROUP_QUEUE, true);
    }


    @Bean
    public Binding satelliteBinding(Queue satelliteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(satelliteQueue).to(exchange).with(SATELLITE_ROUTING_KEY);
    }

    @Bean
    public Binding groupUpdateBinding(Queue groupUpdateQueue, DirectExchange exchange) {
        return BindingBuilder.bind(groupUpdateQueue).to(exchange).with(GROUP_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding satelliteUpdateBinding(Queue satelliteUpdateQueue, DirectExchange exchange) {
        return BindingBuilder.bind(satelliteUpdateQueue).to(exchange).with(SATELLITE_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding satelliteInsertBinding(Queue satelliteInsertQueue, DirectExchange exchange) {
        return BindingBuilder.bind(satelliteInsertQueue).to(exchange).with(SATELLITE_INSERT_ROUTING_KEY);
    }
}