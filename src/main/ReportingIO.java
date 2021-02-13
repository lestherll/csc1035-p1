package main;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ReportingIO {

    private final Scanner scanner = new Scanner(System.in);
    private final Reporting reporting = new Reporting();
    private final DecimalFormat df = new DecimalFormat(".##");

    public int enterNum(int min, int max, String message) {
        int number = min;
        do {
            if ((number > max) || (number < min)) {
                System.out.println("Outside of range");
            }
            System.out.printf("\n%s: ", message);
            while (!this.scanner.hasNextInt() || !scanner.hasNextDouble()) {
                String input = this.scanner.next();
                System.out.printf("%s invalid input, try again: ", input);
            }
            number = this.scanner.nextInt();
        } while ((number > max) || (number < min));
        this.scanner.nextLine();    // consume rest of the line
        return number;
    }

    public double enterDouble(String message) {
        System.out.printf("\n%s: ", message);
        double doubleNum = this.scanner.nextDouble();
        scanner.nextLine();
        return doubleNum;
    }

    public String enterStr(String message) {
        System.out.printf("\n%s: ", message);
        return this.scanner.nextLine();
    }

    public District enterDistrict() {
        String name = this.enterStr("Enter District Name");
        return new District(name);
    }

    public District enterDistrict(Incident incident) {
        District district = this.enterDistrict();
        district.addIncident(incident);

        return district;
    }

    public Incident enterIncident() {
        String postCode = this.enterStr("Enter Postcode");
        int month = this.enterNum(1, 12, "Enter month number");
        int year = this.enterNum(1950, 2021, "Enter year");
        double value = this.enterDouble("Enter value stolen");

        return new Incident(postCode, month, year, value);
    }

    public void displayDistricts() {
        int max = this.reporting.getDistricts().size();
        System.out.println("\n0. Enter new district");
        for (int i = 0; i < max; i++) {
            System.out.printf("%d. %s \n", i+1, this.reporting.getDistricts().get(i));
        }
    }

    public void addIncidentToDistrict(int num, Incident incident) {
        num = num-1;
        this.reporting.getDistricts().get(num).addIncident(incident);
    }

    public void presentReport(int year, double value) {
        /*
        TODO:
        - add year parameter
        - add amount or value parameter
        */

        System.out.printf("\nDistrict with highest average in %d: ", year);
        System.out.println(reporting.maxAverageValInDistAt(year));
        System.out.print("Incident with greatest value stolen: ");
        System.out.println(reporting.maxIncidentVal());
        System.out.printf("Incident with value greater than %s: ", df.format(value));
        System.out.println(reporting.getIncidentWithValGreaterThan(value));
    }

    public void main() {
        int choice;

        while (true) {
            System.out.println(reporting.getDistricts());   // For debugging and testing only
            choice = this.enterNum(1, 4, "Enter choice [1-4]");
            switch (choice) {
                case 1 -> {
                    System.out.println("DISTRICT MENU");
                    District newDistrict = this.enterDistrict();
                    this.reporting.addDistrict(newDistrict);
                }
                case 2 -> {
                    System.out.println("INCIDENT MENU");
                    Incident newIncident = this.enterIncident();

                    this.displayDistricts();
                    int districtNum = this.enterNum(0, this.reporting.getDistricts().size(), "Enter dist number");
                    if (districtNum == 0) {
                        District newDistrict = this.enterDistrict(newIncident);
                        this.reporting.addDistrict(newDistrict);
                    }
                    else {
                        this.addIncidentToDistrict(districtNum, newIncident);
                    }
                }

                case 3 -> {
                    System.out.println("REPORT");
                    this.presentReport(2001, 250);
                }
            }

            if (choice == 4) {
                System.out.println("BYE!!!!!!!");
                break;
            }
        }
    }
}
