package main;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ReportingIO {

    private final Scanner scanner = new Scanner(System.in);
    private final Reporting reporting = new Reporting();
    private final DecimalFormat df = new DecimalFormat(".##");

    public int enterNum(int min, int max, String message) {
        int number;
        while (true){
            System.out.printf("\n%s: ", message);
            try {
                number = Integer.parseInt(this.scanner.next());
                if ((number <= max) && (number >= min)) {
                    break;
                }
                System.out.printf("%s Outside of range\n", number);
            }
            catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
            }
        }
        this.scanner.nextLine();    // consume rest of the line
        return number;
    }

    public double enterDouble(String message) {
        double number;

        while (true) {
            System.out.printf("\n%s: ", message);
            try {
                number = Double.parseDouble(this.scanner.next());
                break;
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
            }
        }
        return number;
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
        District maxAverage = reporting.maxAverageValInDistAt(year);
        Incident maxIncidentVal = reporting.maxIncidentVal();
        List<Incident> incidents = reporting.getIncidentWithValGreaterThan(value);

        System.out.printf("\nDistrict with highest average in %d: ", year);
        if (maxAverage != null) {
            System.out.println(maxAverage.getName() + " with £" + maxAverage.getAverageValAt(year));
        } else {
            System.out.println("None");
        }

        System.out.print("Incident with greatest value stolen: ");
        if (maxIncidentVal != null) {
            System.out.println(maxIncidentVal);
        } else {
            System.out.println("None");
        }

        System.out.printf("Incident with value greater than %s: ", df.format(value));
        if (incidents.size() != 0) {
            System.out.println(incidents);
        } else {
            System.out.println("None");
        }
    }

    private void mainMenu () {
        System.out.println("\n1. Enter District");
        System.out.println("2. Enter Incident");
        System.out.println("3. Present Report");
        System.out.println("4. EXIT");
    }

    public void main() {
        int choice;
        System.out.println("WELCOME TO CRIME REPORT TRACKER!!!");
        while (true) {
            this.mainMenu();
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
