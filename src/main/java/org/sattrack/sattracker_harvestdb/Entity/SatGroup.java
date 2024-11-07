package org.sattrack.sattracker_harvestdb.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sat_groups")
public class SatGroup {
    @Id
    @Column(name = "sat_group_id", nullable = false)
    private Integer id;

    @Column(name = "sat_group_name", nullable = false, length = Integer.MAX_VALUE)
    private String satGroupName;

    @Column(name = "sat_group_query", nullable = false, length = Integer.MAX_VALUE)
    private String satGroupQuery;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSatGroupName() {
        return satGroupName;
    }

    public void setSatGroupName(String satGroupName) {
        this.satGroupName = satGroupName;
    }

    public String getSatGroupQuery() {
        return satGroupQuery;
    }

    public void setSatGroupQuery(String satGroupQuery) {
        this.satGroupQuery = satGroupQuery;
    }

}