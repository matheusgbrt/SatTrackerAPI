package org.sattrack.sattracker_harvestdb.Services;

import org.sattrack.sattracker_harvestdb.Configuration.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitPublisherService {

    final RabbitTemplate rabbitTemplate;

    public RabbitPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendGroupUpdateMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.GROUP_UPDATE_ROUTING_KEY, message);
    }

    public void sendSatelliteMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.SATELLITE_ROUTING_KEY, message);
    }

    public void sendSatelliteInsertMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.SATELLITE_INSERT_ROUTING_KEY, message);
    }

    public void sendSatelliteUpdateMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.SATELLITE_UPDATE_ROUTING_KEY, message);
    }

}