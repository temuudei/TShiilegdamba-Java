package learn.field_agent.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecurityClearance {

    private int securityClearanceId;
    private String name;
    private List<AgencyAgent> agents = new ArrayList<>();

    public SecurityClearance() {}

    public SecurityClearance(int securityClearanceId, String name) {
        this.securityClearanceId = securityClearanceId;
        this.name = name;
    }

    public int getSecurityClearanceId() {
        return securityClearanceId;
    }

    public void setSecurityClearanceId(int securityClearanceId) {
        this.securityClearanceId = securityClearanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AgencyAgent> getAgents() {
        return agents;
    }

    public void setAgents(List<AgencyAgent> agents) {
        this.agents = agents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityClearance that = (SecurityClearance) o;
        return securityClearanceId == that.securityClearanceId && Objects.equals(name, that.name) && Objects.equals(agents, that.agents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityClearanceId, name, agents);
    }

    @Override
    public String toString() {
        return "SecurityClearance{" +
                "securityClearanceId=" + securityClearanceId +
                ", name='" + name + '\'' +
                ", agents=" + agents +
                '}';
    }
}
