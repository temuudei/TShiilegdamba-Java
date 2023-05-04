package learn.field_agent.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mission {
    private int missionId;
    private String code_name;
    private String notes;
    private LocalDate startDate;
    private LocalDate projectedEndDate;
    private LocalDate actualEndDate;
    private BigDecimal operationsCost;
    private int agencyId;
    private List<Agent> agents = new ArrayList<>();

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getProjectedEndDate() {
        return projectedEndDate;
    }

    public void setProjectedEndDate(LocalDate projectedEndDate) {
        this.projectedEndDate = projectedEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public BigDecimal getOperationsCost() {
        return operationsCost;
    }

    public void setOperationsCost(BigDecimal operationsCost) {
        this.operationsCost = operationsCost;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
