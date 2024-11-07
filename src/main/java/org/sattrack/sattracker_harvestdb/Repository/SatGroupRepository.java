package org.sattrack.sattracker_harvestdb.Repository;

import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatGroupRepository extends JpaRepository<SatGroup, Integer> {
    SatGroup findBySatGroupQuery(String query);
}
