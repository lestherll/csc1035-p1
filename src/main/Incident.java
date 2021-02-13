package main;

/**
 * The Incident class represents burglaries that occur.
 * It records where it happens, using a postcode, when
 * it happens using month and year, and how much was
 * stolen. This is the backbone of every other class
 * in this program.
 */
public class Incident {
    private String postCode;
    private int month;
    private int year;
    private double value;

    public Incident() {}

    /**
     *
     * @param postCode the postcode where the incident happened
     * @param month the month(int) when in happened
     * @param year the year(int) the incident happened
     * @param value the amount of value that was stolen
     */
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
