package org.sattrack.sattracker_harvestdb.Repository;

import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.sattrack.sattracker_harvestdb.Entity.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface SatelliteRepository extends JpaRepository<Satellite,Long> {

    List<Satellite> findAllBySatGroup(SatGroup satGroup);

}
