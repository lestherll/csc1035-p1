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

    @Override
    public String toString() {
        return "Incident{" +
                "postCode='" + postCode + '\'' +
                ", month=" + month +
                ", year=" + year +
                ", value=" + value +
                '}';
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
