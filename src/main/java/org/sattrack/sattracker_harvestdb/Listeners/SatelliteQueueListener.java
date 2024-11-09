package org.sattrack.sattracker_harvestdb.Listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sattrack.sattracker_harvestdb.Messages.SatelliteMessage;
import org.sattrack.sattracker_harvestdb.Services.DataHarvestingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SatelliteQueueListener {

    final ObjectMapper objectMapper;
    final DataHarvestingService dataHarvestingService;

    public SatelliteQueueListener(ObjectMapper objectMapper, DataHarvestingService dataHarvestingService) {
        this.objectMapper = objectMapper;
        this.dataHarvestingService = dataHarvestingService;
    }


    @RabbitListener(queues = "satelliteQueue", concurrency = "3-10")
    public void receiveMessage(String message) {
        System.out.println(message);
        try{
            SatelliteMessage satMessage = objectMapper.readValue(message, SatelliteMessage.class);
            dataHarvestingService.HandleAPIData(satMessage);
        }catch(Exception e){
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }

}
