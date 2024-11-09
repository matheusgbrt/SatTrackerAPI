package org.sattrack.sattracker_harvestdb.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sattrack.sattracker_harvestdb.Entity.Satellite;
import org.sattrack.sattracker_harvestdb.Messages.SatelliteMessage;
import org.sattrack.sattracker_harvestdb.Records.CelestrakSatelliteData;
import org.sattrack.sattracker_harvestdb.Repository.SatGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.Optional;

@Service
public class DataHarvestingService {
    final ApiService apiService;
    final SatelliteService satelliteService;
    final SatGroupRepository satGroupRepository;
    final RabbitPublisherService rabbitPublisherService;
    final ObjectMapper mapper;


    public DataHarvestingService(ApiService apiService, SatelliteService satelliteService, SatGroupRepository satGroupRepository, RabbitPublisherService rabbitPublisherService, ObjectMapper mapper) {
        this.apiService = apiService;
        this.satelliteService = satelliteService;
        this.satGroupRepository = satGroupRepository;
        this.rabbitPublisherService = rabbitPublisherService;
        this.mapper = mapper;
    }


    public void consumeGroupQueue(String groupQuery) {
//#TODO: Transformar rotina em assíncrona.
        try {
            CelestrakSatelliteData[] apiData = apiService.getSatelliteGroupData(groupQuery).block();
            assert apiData != null;
            Arrays.stream(apiData).forEach(data -> RegisterSatellite(data, groupQuery));
        } catch (WebClientResponseException e) {
            System.err.println("HTTP Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void RegisterSatellite(CelestrakSatelliteData satelliteData, String groupQuery) {
        try {
            SatelliteMessage satMessage = new SatelliteMessage(satelliteData, groupQuery);
            rabbitPublisherService.sendSatelliteMessage(mapper.writeValueAsString(satMessage));
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }


    public void RegisterSatelliteUpdate(SatelliteMessage message) {
        try {
            rabbitPublisherService.sendSatelliteUpdateMessage(mapper.writeValueAsString(message));
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void RegisterSatelliteInsert(SatelliteMessage message) {
        try {
            rabbitPublisherService.sendSatelliteInsertMessage(mapper.writeValueAsString(message));
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void HandleAPIData(SatelliteMessage message) {
        Optional<Satellite> satellite = satelliteService.GetSatelliteByObjectId(message.satelliteData().OBJECT_ID());
        if (satellite.isEmpty()) {
            RegisterSatelliteInsert(message);
        } else {
            RegisterSatelliteUpdate(message);
        }
    }

}
