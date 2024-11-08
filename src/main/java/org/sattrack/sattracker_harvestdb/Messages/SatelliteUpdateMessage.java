package org.sattrack.sattracker_harvestdb.Messages;

import org.sattrack.sattracker_harvestdb.Entity.Satellite;

import java.io.Serializable;

public class SatelliteUpdateMessage implements Serializable {
    private String objectId;
    private Satellite data;
    private String operationType; // "UPDATE" or "ADD"

    // Constructors, getters, and setters
}