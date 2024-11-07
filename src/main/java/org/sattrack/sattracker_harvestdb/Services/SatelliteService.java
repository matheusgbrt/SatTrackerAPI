package org.sattrack.sattracker_harvestdb.Services;

import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.sattrack.sattracker_harvestdb.Entity.Satellite;
import org.sattrack.sattracker_harvestdb.Repository.SatelliteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatelliteService {
    private final SatelliteRepository satelliteRepository;

    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    public List<Satellite> GetAllFromGroup(SatGroup group){
        return satelliteRepository.findAllBySatGroup(group);
    }


}
