package org.sattrack.sattracker_harvestdb.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sats")
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sats_id_gen")
    @SequenceGenerator(name = "sats_id_gen", sequenceName = "sats_sat_id_seq", allocationSize = 1)
    @Column(name = "sat_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sat_group_id")
    private SatGroup satGroup;

    @Column(name = "object_name", length = Integer.MAX_VALUE)
    private String objectName;

    @Column(name = "object_id", length = Integer.MAX_VALUE)
    private String objectId;

    @Column(name = "epoch")
    private Integer epoch;

    @Column(name = "mean_motion")
    private BigDecimal meanMotion;

    @Column(name = "eccentricity")
    private BigDecimal eccentricity;

    @Column(name = "inclination")
    private BigDecimal inclination;

    @Column(name = "ra_of_asc_node")
    private BigDecimal raOfAscNode;

    @Column(name = "arg_of_pericenter")
    private BigDecimal argOfPericenter;

    @Column(name = "mean_anomaly")
    private BigDecimal meanAnomaly;

    @Column(name = "ephemeris_type")
    private Integer ephemerisType;

    @Column(name = "classification_type", length = Integer.MAX_VALUE)
    private String classificationType;

    @Column(name = "norad_cat_id")
    private Integer noradCatId;

    @Column(name = "element_set_no")
    private Integer elementSetNo;

    @Column(name = "rev_at_epoch")
    private Integer revAtEpoch;

    @Column(name = "bstar")
    private BigDecimal bstar;

    @Column(name = "mean_motion_dot")
    private BigDecimal meanMotionDot;

    @Column(name = "mean_motion_ddot")
    private BigDecimal meanMotionDdot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SatGroup getSatGroup() {
        return satGroup;
    }

    public void setSatGroup(SatGroup satGroup) {
        this.satGroup = satGroup;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getEpoch() {
        return epoch;
    }

    public void setEpoch(Integer epoch) {
        this.epoch = epoch;
    }

    public BigDecimal getMeanMotion() {
        return meanMotion;
    }

    public void setMeanMotion(BigDecimal meanMotion) {
        this.meanMotion = meanMotion;
    }

    public BigDecimal getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(BigDecimal eccentricity) {
        this.eccentricity = eccentricity;
    }

    public BigDecimal getInclination() {
        return inclination;
    }

    public void setInclination(BigDecimal inclination) {
        this.inclination = inclination;
    }

    public BigDecimal getRaOfAscNode() {
        return raOfAscNode;
    }

    public void setRaOfAscNode(BigDecimal raOfAscNode) {
        this.raOfAscNode = raOfAscNode;
    }

    public BigDecimal getArgOfPericenter() {
        return argOfPericenter;
    }

    public void setArgOfPericenter(BigDecimal argOfPericenter) {
        this.argOfPericenter = argOfPericenter;
    }

    public BigDecimal getMeanAnomaly() {
        return meanAnomaly;
    }

    public void setMeanAnomaly(BigDecimal meanAnomaly) {
        this.meanAnomaly = meanAnomaly;
    }

    public Integer getEphemerisType() {
        return ephemerisType;
    }

    public void setEphemerisType(Integer ephemerisType) {
        this.ephemerisType = ephemerisType;
    }

    public String getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(String classificationType) {
        this.classificationType = classificationType;
    }

    public Integer getNoradCatId() {
        return noradCatId;
    }

    public void setNoradCatId(Integer noradCatId) {
        this.noradCatId = noradCatId;
    }

    public Integer getElementSetNo() {
        return elementSetNo;
    }

    public void setElementSetNo(Integer elementSetNo) {
        this.elementSetNo = elementSetNo;
    }

    public Integer getRevAtEpoch() {
        return revAtEpoch;
    }

    public void setRevAtEpoch(Integer revAtEpoch) {
        this.revAtEpoch = revAtEpoch;
    }

    public BigDecimal getBstar() {
        return bstar;
    }

    public void setBstar(BigDecimal bstar) {
        this.bstar = bstar;
    }

    public BigDecimal getMeanMotionDot() {
        return meanMotionDot;
    }

    public void setMeanMotionDot(BigDecimal meanMotionDot) {
        this.meanMotionDot = meanMotionDot;
    }

    public BigDecimal getMeanMotionDdot() {
        return meanMotionDdot;
    }

    public void setMeanMotionDdot(BigDecimal meanMotionDdot) {
        this.meanMotionDdot = meanMotionDdot;
    }

}