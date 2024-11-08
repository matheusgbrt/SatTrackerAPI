package org.sattrack.sattracker_harvestdb.Services;

import org.sattrack.sattracker_harvestdb.Configuration.RabbitMQConfig;
import org.sattrack.sattracker_harvestdb.Messages.SatelliteUpdateMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SatellitePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUpdateMessage(SatelliteUpdateMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.UPDATE_ROUTING_KEY, message);
    }
}