package org.sattrack.sattracker_harvestdb.Services;

import jakarta.transaction.Transactional;
import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.sattrack.sattracker_harvestdb.Entity.Satellite;
import org.sattrack.sattracker_harvestdb.Records.CelestrakSatelliteData;
import org.sattrack.sattracker_harvestdb.Repository.SatGroupRepository;
import org.sattrack.sattracker_harvestdb.Repository.SatelliteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SatelliteService {
    private final SatelliteRepository satelliteRepository;
    private final SatGroupRepository satGroupRepository;

    public SatelliteService(SatelliteRepository satelliteRepository, SatGroupRepository satGroupRepository) {
        this.satelliteRepository = satelliteRepository;
        this.satGroupRepository = satGroupRepository;
    }

    public List<Satellite> GetAllFromGroup(SatGroup group){
        return satelliteRepository.findAllBySatGroup(group);
    }

    public Optional<Satellite> GetSatelliteByObjectId(String objectId){ return Optional.ofNullable(satelliteRepository.findSatelliteByObjectId(objectId));
    };

    @Transactional
    public void UpdateFromAPI(CelestrakSatelliteData celestrakSatelliteData) {
        Satellite satellite = satelliteRepository.findSatelliteByObjectId(celestrakSatelliteData.OBJECT_ID());
        satellite.setBstar(BigDecimal.valueOf(celestrakSatelliteData.BSTAR()));
        satellite.setMeanMotionDot(BigDecimal.valueOf(celestrakSatelliteData.MEAN_MOTION_DOT()));
        satellite.setMeanMotionDdot(BigDecimal.valueOf(celestrakSatelliteData.MEAN_MOTION_DDOT()));
        satellite.setRevAtEpoch(celestrakSatelliteData.REV_AT_EPOCH());
        satellite.setMeanAnomaly(BigDecimal.valueOf(celestrakSatelliteData.MEAN_ANOMALY()));
        satellite.setArgOfPericenter(BigDecimal.valueOf(celestrakSatelliteData.ARG_OF_PERICENTER()));
        satellite.setRaOfAscNode(BigDecimal.valueOf(celestrakSatelliteData.RA_OF_ASC_NODE()));
        satellite.setInclination(BigDecimal.valueOf(celestrakSatelliteData.INCLINATION()));
        satellite.setEccentricity(BigDecimal.valueOf(celestrakSatelliteData.ECCENTRICITY()));
        satellite.setMeanMotion(BigDecimal.valueOf(celestrakSatelliteData.MEAN_MOTION()));
        satellite.setEpoch(LocalDateTime.parse(celestrakSatelliteData.EPOCH()));
        satelliteRepository.save(satellite);
    }

    @Transactional
    public void addNewSatelliteFromAPI(CelestrakSatelliteData celestrakData, String groupQuery) {
        Optional<SatGroup> satGroup = satGroupRepository.findBySatGroupQuery(groupQuery);
        if (satGroup.isPresent()) {
            Satellite satellite = new Satellite();
            satellite.setSatGroup(satGroup.get());
            satellite.setObjectName(celestrakData.OBJECT_NAME());
            satellite.setObjectId(celestrakData.OBJECT_ID());
            satellite.setEpoch(LocalDateTime.parse(celestrakData.EPOCH()));
            satellite.setMeanMotion(BigDecimal.valueOf(celestrakData.MEAN_MOTION()));
            satellite.setEccentricity(BigDecimal.valueOf(celestrakData.ECCENTRICITY()));
            satellite.setInclination(BigDecimal.valueOf(celestrakData.INCLINATION()));
            satellite.setRaOfAscNode(BigDecimal.valueOf(celestrakData.RA_OF_ASC_NODE()));
            satellite.setArgOfPericenter(BigDecimal.valueOf(celestrakData.ARG_OF_PERICENTER()));
            satellite.setMeanAnomaly(BigDecimal.valueOf(celestrakData.MEAN_ANOMALY()));
            satellite.setEphemerisType(celestrakData.EPHEMERIS_TYPE());
            satellite.setClassificationType(celestrakData.CLASSIFICATION_TYPE());
            satellite.setNoradCatId(celestrakData.NORAD_CAT_ID());
            satellite.setElementSetNo(celestrakData.ELEMENT_SET_NO());
            satellite.setRevAtEpoch(celestrakData.REV_AT_EPOCH());
            satellite.setBstar(BigDecimal.valueOf(celestrakData.BSTAR()));
            satellite.setMeanMotionDot(BigDecimal.valueOf(celestrakData.MEAN_MOTION_DOT()));
            satellite.setMeanMotionDdot(BigDecimal.valueOf(celestrakData.MEAN_MOTION_DDOT()));
            satelliteRepository.save(satellite);
        }
    }
}
