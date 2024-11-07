package org.sattrack.sattracker_harvestdb.Records;

public record CelestrakSatelliteData(
        String OBJECT_NAME,
        String OBJECT_ID,
        String EPOCH,
        double MEAN_MOTION,
        double ECCENTRICITY,
        double INCLINATION,
        double RA_OF_ASC_NODE,
        double ARG_OF_PERICENTER,
        double MEAN_ANOMALY,
        int EPHEMERIS_TYPE,
        String CLASSIFICATION_TYPE,
        int NORAD_CAT_ID,
        int ELEMENT_SET_NO,
        int REV_AT_EPOCH,
        double BSTAR,
        double MEAN_MOTION_DOT,
        double MEAN_MOTION_DDOT
) {}
