package com.oracle.construction.pojo;


public class Project {
    private int customerId;
    private int contractId;
    private Zone geoZone;
    private String teamCode;
    private String projectCode;
    private int buildDuration;

    public Project() {
    }

    public Project(int customerId, int contractId, Zone geoZone, String teamCode, String projectCode, int buildDuration) {
        this.customerId = customerId;
        this.contractId = contractId;
        this.geoZone = geoZone;
        this.teamCode = teamCode;
        this.projectCode = projectCode;
        this.buildDuration = buildDuration;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public Zone getGeoZone() {
        return geoZone;
    }

    public void setGeoZone(Zone geoZone) {
        this.geoZone = geoZone;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDuration(int buildDuration) {
        this.buildDuration = buildDuration;
    }

    @Override
    public String toString() {
        return "Project{" +
                "customerId=" + customerId +
                ", contractId=" + contractId +
                ", geoZone=" + geoZone +
                ", teamCode='" + teamCode + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", buildDuration='" + buildDuration + '\'' +
                '}';
    }
}
