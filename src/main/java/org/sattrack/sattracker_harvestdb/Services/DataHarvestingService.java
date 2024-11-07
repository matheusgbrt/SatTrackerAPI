package org.sattrack.sattracker_harvestdb.Services;

import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.sattrack.sattracker_harvestdb.Entity.Satellite;
import org.sattrack.sattracker_harvestdb.Records.CelestrakSatelliteData;
import org.sattrack.sattracker_harvestdb.Repository.SatGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataHarvestingService {
    final ApiService apiService;
    final SatelliteService satelliteService;
    final SatGroupRepository satGroupRepository;
    final String format = "json";


    public DataHarvestingService(ApiService apiService, SatelliteService satelliteService, SatGroupRepository satGroupRepository) {
        this.apiService = apiService;
        this.satelliteService = satelliteService;
        this.satGroupRepository = satGroupRepository;
    }

    public void init(String satGroupQuery){
        //Get Registered SatGroup
        SatGroup satGroup = satGroupRepository.findBySatGroupQuery(satGroupQuery);
        if(satGroup == null){
            return;
        }
        //Get satellites from API.
        CelestrakSatelliteData[] satData = apiService.getSatelliteGroupData(satGroupQuery,format).block();
        //Query the database
        List<Satellite> sats = satelliteService.GetAllFromGroup(satGroup);

    }

}
