package org.sattrack.sattracker_harvestdb.Services;

import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.sattrack.sattracker_harvestdb.Entity.Satellite;
import org.sattrack.sattracker_harvestdb.Messages.SatelliteUpdateMessage;
import org.sattrack.sattracker_harvestdb.Records.CelestrakSatelliteData;
import org.sattrack.sattracker_harvestdb.Repository.SatGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DataHarvestingService {
    final ApiService apiService;
    final SatelliteService satelliteService;
    final SatGroupRepository satGroupRepository;
    final String format = "json";
    private final SatellitePublisher satellitePublisher;


    public DataHarvestingService(ApiService apiService, SatelliteService satelliteService, SatGroupRepository satGroupRepository, SatellitePublisher satellitePublisher) {
        this.apiService = apiService;
        this.satelliteService = satelliteService;
        this.satGroupRepository = satGroupRepository;
        this.satellitePublisher = satellitePublisher;
    }

    public void init(String satGroupQuery){
        //Get Registered SatGroup
        SatGroup satGroup = satGroupRepository.findBySatGroupQuery(satGroupQuery);
        //Guarantee the group is in the db
        if(satGroup == null){
            return;
        }
        //Get satellites from API.
        CelestrakSatelliteData[] celestrakData = apiService.getSatelliteGroupData(satGroupQuery,format).block();
        //Query the database
        List<Satellite> databaseData = satelliteService.GetAllFromGroup(satGroup);
        //Guarantee there is a return;
        assert celestrakData != null;
        //Handle the data
        HandleAPIData(databaseData, celestrakData,satGroup);
    }

    private void HandleAPIData(List<Satellite> databaseData,CelestrakSatelliteData[] celestrakData,SatGroup satGroup){
        Arrays.stream(celestrakData).forEach(data -> {
            Optional<Satellite> result = databaseData.stream().filter(satellite ->
                    data.OBJECT_ID().equals(satellite.getObjectId())).findFirst();
            if(result.isPresent()){
                SatelliteUpdateMessage message = new SatelliteUpdateMessage();
                //satellitePublisher.sendUpdateMessage();
            }else{
                satelliteService.addNewSatelliteFromAPI(data,satGroup);
            }
        });
    }

}
