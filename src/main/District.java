package main;

import java.util.*;

/**
 * District represents the class that will contain all Incidents
 * that are located in the same region or area. A district will
 * have a name and the list of incidents.
 */
public class District {
    private String name;
    private List<Incident> incidents = new ArrayList<>();

    /**
     * Construct a district only with a name
     * @param name The name of the district
     */
    public District(String name) {
        this.name = name;
    }

    /**
     * Construct a district with name and incidents
     * @param name the name of the District
     * @param incidents the list of incidents to be added to District
     */
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

    /**
     * Checks if two districts are equal: same name and class
     * @param other The other object that this object is being compared to
     * @return boolean value of the comparison
     */
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

    /**
     * Add a single incident to this.incidents field
     * @param incident an incident to be added to list of all incidents
     */
    public void addIncident (Incident incident) {
        this.incidents.add(incident);
    }

    /**
     * A multiple incidents in List form to this.incidents field
     * @param incidents the incidents that will be added
     */
    public void addIncident (List<Incident> incidents) {
        this.incidents.addAll(incidents);
    }

    /**
     * Add multiple incidents in Array form to this.incidents
     * @param incidents the incidents that will be added
     */
    public void addIncident (Incident[] incidents) {
        this.addIncident(Arrays.asList(incidents));
    }

    /**
     * Average value of incidents in a given year
     * @param year Year wanted for the average
     * @return the value of the average
     */
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

    /**
     * Greatest value stolen in an Incident from a district
     * @return the greatest value stolen
     */
    public Incident getHighestVal() {
        if (this.incidents.size() == 0) {
            return null;
        }

        if (this.incidents.size() ==1 ) {
            return this.incidents.get(0);
        }

        Incident highest = this.incidents.get(0);
        for (int i=1; i < this.incidents.size(); i++) {
            if (this.incidents.get(i) != null) {
                if (this.incidents.get(i).getValue() > highest.getValue()) {
                    highest = this.incidents.get(i);
                }
            }
        }
        return highest;
    }

    /**
     * Incidents with greater value than the given amount
     * @param val the value that will be used as reference
     * @return the list of Incidents with greater value than the given amount
     */
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
