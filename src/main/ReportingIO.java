package main;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

/**
 * I/O class. This class enables the main user interface for the whole
 * program such that the user can interact with the program. This class
 * allows the use to add new Districts and Incidents or even generate
 * reports for all of the details that had been entered during the
 * runtime.
 */
public class ReportingIO {

    private final Scanner scanner = new Scanner(System.in);
    private final Reporting reporting = new Reporting();
    private final DecimalFormat df = new DecimalFormat(".##");

    /**
     * This allows the user to enter integer inputs based on the minimum
     * and maximum arguments that have been passed by the user
     * @param min this depicts the minimum integer input
     * @param max this depicts the maximum integer input
     * @param message the message shown when the program asks for input
     * @return the valid input by the user
     */
    private int enterNum(int min, int max, String message) {
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

    /**
     * A basic double input for the user
     * @param message argument that the user must pass to be
     *                displayed when the program asks for input
     * @return validated input that the user will enter
     */
    private double enterDouble(String message) {
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

    /**
     * Allows the user to input string
     * @param message the message shown when the program asks for input
     * @return the input that the user entered in string form
     */
    private String enterStr(String message) {
        System.out.printf("\n%s: ", message);
        return this.scanner.nextLine();
    }

    /**
     * Creates a district with the user input as argument
     * @return the District object with the user input as name
     */
    private District enterDistrict() {
        String name = this.enterStr("Enter District Name");
        return new District(name);
    }

    /**
     * Creates a district with the user input as argument
     * @param incident the incident to be added to the District
     *                 object when it is instantiated
     * @return the District object with an Incident object
     */
    private District enterDistrict(Incident incident) {
        District district = this.enterDistrict();
        district.addIncident(incident);

        return district;
    }

    /**
     * Create an Incident object with the inputs given when the
     * program asked for it. User can only enter 1-12 for year
     * and only 1950 to 2021 for year. They can change the range
     * of the year manually if need be.
     * @return Incident object
     */
    private Incident enterIncident() {
        String postCode = this.enterStr("Enter Postcode");
        int month = this.enterNum(1, 12, "Enter month number [1-12]");
        int year = this.enterNum(1950, 2021, "Enter year [1950-2021]");
        double value = this.enterDouble("Enter value stolen");

        return new Incident(postCode, month, year, value);
    }

    /**
     * Display all districts that currently exists
     */
    private void displayDistricts() {
        int max = this.reporting.getDistricts().size();
        System.out.println("\n0. Enter new district");
        for (int i = 0; i < max; i++) {
            System.out.printf("%d. %s \n", i+1, this.reporting.getDistricts().get(i));
        }
    }

    /**
     * Add incidents to specified districts (by position)
     * @param num position of the district minus 1 i.e. passing 1
     *            will fetch the value of index 1-1 which is 0
     * @param incident the Incident object to be added to the District
     *                 at index num-1
     */
    private void addIncidentToDistrict(int num, Incident incident) {
        num = num-1;
        this.reporting.getDistricts().get(num).addIncident(incident);
    }

    /**
     * Present a comprehensible report to the user. The user must enter
     * the year they want for the greatest average value at a district
     * in a year, and value that they want to compare to.
     * @param year the year that the greatest average of a district by
     *             incident value is to be presented
     * @param value the value to be compared to such that the incident
     *              value is greater than it
     */
    private void presentReport(int year, double value) {
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

    /**
     * The main menu loop where the user has to enter their inputs
     * and interact with the program. This allows them to give details
     * about the incidents and the Districts.
     */
    public void main() {
        int choice;
        System.out.println("WELCOME TO CRIME REPORT TRACKER!!!");
        while (true) {
            this.mainMenu();
            choice = this.enterNum(1, 4, "Enter choice [1-4]");
            switch (choice) {
                // Enter district
                case 1 -> {
                    System.out.println("DISTRICT MENU");
                    District newDistrict = this.enterDistrict();
                    this.reporting.addDistrict(newDistrict);
                }
                // Enter incident
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
                // Present report
                case 3 -> {
                    System.out.println("REPORT");
                    if (this.reporting.getDistricts().size() != 0) {
                        int year = this.enterNum(1900, 2001, "Enter Year");
                        double value = this.enterDouble("Enter value higher than");
                        this.presentReport(year, value);
                    }
                    else {
                        System.out.println("There are no incidents");
                    }
                }
            }
            // Exit main menu
            if (choice == 4) {
                System.out.println("BYE!!!!!!!");
                break;
            }
        }
    }
}
