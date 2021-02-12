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

    @Override
    public boolean equals(Object other) {
        if (other instanceof District) {
            return ((District) other).name.equals(this.name);
        }
        return false;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
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

    public double getAverageValAt(int year) {
        if (this.incidents.size() == 0) {
            return 0;
        }

        double total = 0;
        for (Incident incident: this.incidents) {
            if (incident.getYear() == year) {
                total += incident.getValue();
            }
        }
        return total/incidents.size();
    }

    public Incident getHighestVal() {
        if (this.incidents.size() == 0) {
            return null;
        }

        if (this.incidents.size() ==1 ) {
            return this.incidents.get(0);
        }

        Incident highest = this.incidents.get(0);
        for (int i=1; i < this.incidents.size(); i++) {
            if (this.incidents.get(i).getValue() > highest.getValue()) {
                highest = this.incidents.get(i);
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
