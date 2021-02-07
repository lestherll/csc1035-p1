package main;

public class Incident {
    private String postCode;
    private int month;
    private int year;
    private double value;

    public Incident(String postCode, int month, int year, double value) {
        this.postCode = postCode;
        this.month = month;
        this.year = year;
        this.value = value;
    }

}
