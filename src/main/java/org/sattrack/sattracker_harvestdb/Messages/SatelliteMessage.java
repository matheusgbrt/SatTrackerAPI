package org.sattrack.sattracker_harvestdb.Messages;

import org.sattrack.sattracker_harvestdb.Records.CelestrakSatelliteData;

import java.io.Serializable;

public record SatelliteMessage(CelestrakSatelliteData satelliteData,String groupQuery) implements Serializable{}
