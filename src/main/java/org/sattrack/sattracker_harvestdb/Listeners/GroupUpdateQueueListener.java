package org.sattrack.sattracker_harvestdb.Listeners;

import org.sattrack.sattracker_harvestdb.Services.DataHarvestingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class GroupUpdateQueueListener {


    final DataHarvestingService dataHarvestingService;

    public GroupUpdateQueueListener(DataHarvestingService dataHarvestingService) {
        this.dataHarvestingService = dataHarvestingService;
    }

    @RabbitListener(queues = "satelliteGroupQueue",concurrency = "3-10")
    public void receiveMessage(String message) {
        dataHarvestingService.consumeGroupQueue(message);
    }
}
