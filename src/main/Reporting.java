package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reporting {
    private final List<District> districts = new ArrayList<>();

    public Reporting() {}

    public Reporting(List<District> districts) {
        this.districts.addAll(districts);
    }

    public Reporting (District[] districts) {
        this(Arrays.asList(districts));
    }

    @Override
    public String toString() {
        return "Reporting{" +
                "districts=" + districts +
                '}';
    }

    public List<District> getDistricts() {
        return this.districts;
    }

    public void addDistrict(District... districts) {
        this.districts.addAll(Arrays.asList(districts));
    }

    public District maxAverageValInDistAt(int year) {
        District highest = this.districts.get(0);
        for (int i=1; i < this.districts.size(); i++) {
            if (this.districts.get(i).getAverageValAt(year) > highest.getAverageValAt(year)) {
                highest = this.districts.get(i);
            }
        }
        return highest;
    }

    public Incident maxIncidentVal() {
        if (this.districts.size() == 0) {
            return null;
        }

        Incident highest = this.districts.get(0).getHighestVal();
        for (int i = 1; i < this.districts.size(); i++) {
            if (this.districts.get(i).getHighestVal().getValue() > highest.getValue()) {
                highest = districts.get(i).getHighestVal();
            }
        }
        return highest;
    }

}






