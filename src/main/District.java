package main;

import java.util.*;

public class District {
    private String name;
    private List<Incident> incidents = new ArrayList<>();

    public District(String name) {
        this.name = name;
    }

    public District(String name, List<Incident> incidents) {
        this(name);
        this.incidents.addAll(incidents);
    }

    public District(String name, Incident[] incidents) {
        this(name, Arrays.asList(incidents));
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

    public void addIncident (List<Incident> incidents) {
        this.incidents.addAll(incidents);
    }

    public void addIncident (Incident[] incidents) {
        this.addIncident(Arrays.asList(incidents));
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

    public List<Incident> getIncidentWithValGreaterThan(double val) {
        List<Incident> valArr = new ArrayList<>();
        for (Incident incident: this.incidents) {
            if (incident.getValue() > val) {
                valArr.add(incident);
            }
        }
        return valArr;
    }
}
