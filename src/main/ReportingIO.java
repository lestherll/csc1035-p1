package main;

import java.util.Scanner;

public class ReportingIO {

    private final Scanner scanner = new Scanner(System.in);
    private final Reporting reporting = new Reporting();

    public int enterNum(int min, int max, String message) {
        int number = min;
        do {
            if ((number > max) || (number < min)) {
                System.out.println("Outside of range");
            }
            System.out.printf("%s: ", message);
            while (!this.scanner.hasNextInt()) {
                String input = this.scanner.next();
                System.out.printf("%s invalid input, try again: ", input);
            }
            number = this.scanner.nextInt();
        } while ((number > max) || (number < min));
        return number;
    }

    public String enterStr(String message) {
        System.out.printf("%s: ", message);
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
        double value = this.enterNum(0, (int) Double.POSITIVE_INFINITY, "Enter value stolen");

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

    public void presentReport() {
        /*
        TODO:
        - add year parameter
        - add amount or value parameter
        */

        System.out.print("\nDistrict with highest average in year: ");
        System.out.println(reporting.maxAverageValInDistAt(2001));
        System.out.print("Incident with greatest value stolen: ");
        System.out.println(reporting.maxIncidentVal());
        System.out.print("Incident with value greater than: ");
        System.out.println(reporting.getIncidentWithValGreaterThan(100));
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
                    this.presentReport();
                }
            }

            if (choice == 4) {
                System.out.println("BYE!!!!!!!");
                break;
            }
        }
    }
}
