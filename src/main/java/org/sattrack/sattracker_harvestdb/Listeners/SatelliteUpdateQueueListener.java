package org.sattrack.sattracker_harvestdb.Listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sattrack.sattracker_harvestdb.Messages.SatelliteMessage;
import org.sattrack.sattracker_harvestdb.Services.SatelliteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SatelliteUpdateQueueListener {

    final ObjectMapper objectMapper;
    final SatelliteService satelliteService;

    public SatelliteUpdateQueueListener(ObjectMapper objectMapper, SatelliteService satelliteService) {
        this.objectMapper = objectMapper;
        this.satelliteService = satelliteService;
    }

    @RabbitListener(queues = "satelliteUpdateQueue",concurrency = "3-10")
    public void ReceiveMessage(String message){
        try{
            SatelliteMessage satMessage = objectMapper.readValue(message, SatelliteMessage.class);
            satelliteService.UpdateFromAPI(satMessage.satelliteData());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}