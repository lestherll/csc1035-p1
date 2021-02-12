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

    public void enterDistrict() {
        String name = this.enterStr("Enter District Name");
        District district = new District(name);
        this.reporting.addDistrict(district);
    }

    public void enterIncident() {
        String postCode = this.enterStr("Enter Postcode");
        int month = this.enterNum(1, 12, "Enter month number");
        int year = this.enterNum(1950, 2021, "Enter year");
        double value = this.enterNum(0, (int) Double.POSITIVE_INFINITY, "Enter value stolen");
        Incident incident = new Incident(postCode, month, year, value);
    }

    public void presentReport() {
        System.out.println(reporting.maxAverageValInDistAt(2001));
        System.out.println(reporting.maxIncidentVal());
        System.out.println(reporting.getIncidentWithValGreaterThan(100));
    }

    public void main() {
        int choice;

        while (true) {
            choice = this.enterNum(1, 4, "Enter choice [1-4]");
            this.scanner.nextLine();    // consume rest of the line
            switch (choice) {
                case 1 -> {
                    System.out.println("DISTRICT MENU");
                    this.enterDistrict();
                }
                case 2 -> {
                    System.out.println("INCIDENT MENU");
                    this.enterIncident();
                }
                case 3 -> this.presentReport();
            }

            if (choice == 4) {
                System.out.println("BYE!!!!!!!");
                break;
            }
        }
    }
}
