package main;

import java.util.*;

public class District {
    private String name;
    private List<Incident> incidents = new ArrayList<>();

    public District(String name) {
        this.name = name;
    }

    public District(String name, List<Incident> incidents) {
        this.name = name;
        this.incidents = incidents;
    }

    public District(String name, Incident[] incidents) {
        this.name = name;
        this.incidents = Arrays.asList(incidents);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Incident> getIncidents() {
        return this.incidents;
    }

    @Override
    public String toString() {
        return "District{" +
                "name='" + name + '\'' +
                ", incidents=" + incidents +
                '}';
    }

    public void addIncident (Incident incident) {
        this.incidents.add(incident);
    }

    public Double getAverageVal() {
        double total = 0;
        for (Incident incident : this.incidents) {
            total += incident.getValue();
        }
        return total/this.incidents.size();
    }

    public Double getHighestVal() {
        double highest = 0;
        double curr;
        for (Incident incident : this.incidents) {
            curr = incident.getValue();
            if (highest < curr) {
                highest = curr;
            }
        }
        return highest;
    }
}
