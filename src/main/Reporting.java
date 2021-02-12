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


}






