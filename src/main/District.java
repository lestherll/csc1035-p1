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
        this.incidents.addAll(Arrays.asList(incidents));
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

    // Helper method for fetching all values stolen from incidents
    private double[] getAllIncidentVal() {
        double[] values = new double[this.incidents.size()];
        for (int i=0; i < this.incidents.size(); i++) {
            values[i] = this.incidents.get(i).getValue();
        }
        return values;
    }

    public double getAverageVal() {
        double total = Arrays.stream(this.getAllIncidentVal()).sum();
        return total/this.incidents.size();
    }

    public double getHighestVal() {
        double highest = 0;
        for (double val: this.getAllIncidentVal()) {
            if (val > highest) {
                highest = val;
            }
        }
        return highest;
    }
}
