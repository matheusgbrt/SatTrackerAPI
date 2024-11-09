package org.sattrack.sattracker_harvestdb.Repository;

import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SatGroupRepository extends JpaRepository<SatGroup, Integer> {
    Optional<SatGroup> findBySatGroupQuery(String query);
}
