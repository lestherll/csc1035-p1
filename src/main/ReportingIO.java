package main;

import java.util.Scanner;

public class ReportingIO {

    private final Scanner scanner = new Scanner(System.in);
    private final Reporting reporting = new Reporting();



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
