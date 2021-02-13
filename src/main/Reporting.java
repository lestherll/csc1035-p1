package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reporting represents the class that will contain all of existing
 * Districts.Reporting will use these district to generate reports
 * and perform basic data analysis.
 */
public class Reporting {
    private final List<District> districts = new ArrayList<>();

    /**
     * Construct an empty reporting object
     */
    public Reporting() {}

    /**
     * Construct a reporting object with list of District
     * @param districts the list of District that will be added to districts field
     */
    public Reporting(List<District> districts) {
        this.districts.addAll(districts);
    }

    /**
     * Construct a reporting object with array of District
     * @param districts array of District to be added to districts field
     */
    public Reporting (District[] districts) {
        this(Arrays.asList(districts));
    }

    @Override
    public String toString() {
        return "Reporting{" +
                "districts=" + districts +
                '}';
    }

    /**
     *
     * @return all districts in the districts field
     */
    public List<District> getDistricts() {
        return this.districts;
    }

    /**
     * Adds one or more District objects to districts field
     * @param districts District/s to be added to districts field
     */
    public void addDistrict(District... districts) {
        this.districts.addAll(Arrays.asList(districts));
    }

    /**
     * Generate the greatest average value stolen in a district at a given year
     * @param year the year wanted for the average
     * @return greatest average value of incidents at a given year
     */
    public District maxAverageValInDistAt(int year) {
        if (this.districts.size() == 0) {
            return null;
        }

        District highest = this.districts.get(0);
        for (int i=1; i < this.districts.size(); i++) {
            if (this.districts.get(i).getAverageValAt(year) > highest.getAverageValAt(year)) {
                highest = this.districts.get(i);
            }
        }

        if (highest.getAverageValAt(year) == 0) {
            return null;
        }

        return highest;
    }

    /**
     * Greatest value stolen in an Incident from all districts
     * @return Incident with the most value stolen from all districts
     */
    public Incident maxIncidentVal() {
        if (this.districts.size() == 0) {
            return null;
        }

        Incident highest = this.districts.get(0).getHighestVal();
        for (int i = 1; i < this.districts.size(); i++) {
            if (highest == null) {
                highest = this.districts.get(i).getHighestVal();
            }

            if (this.districts.get(i).getHighestVal() == null) {
                continue;
            }

            if (this.districts.get(i).getHighestVal().getValue() > highest.getValue()) {
                highest = districts.get(i).getHighestVal();
            }
        }
        return highest;
    }

    /**
     * Incidents with greater value than the given amount on every District
     * @param amount amount to be used for comparison
     * @return list of Incidents that exceed the amount given
     */
    public List<Incident> getIncidentWithValGreaterThan(double amount) {
        List<Incident> incidentList = new ArrayList<>();

        for (District district: this.districts) {
            incidentList.addAll(district.getIncidentWithValGreaterThan(amount));
        }
        return incidentList;
    }
}






