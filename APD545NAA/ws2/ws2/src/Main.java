/**********************************************
 Workshop #
 Course:ADP - Semester
 Last Name:Noori
 First Name:Lebna
 ID:157672205
 Section:NAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date:10-06-2025
 **********************************************/


import controller.VMUMSController;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final VMUMSController controller = new VMUMSController();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Vehicle Maintenance and Usage Management System ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Add Maintenance Record");
            System.out.println("3. Add Usage Log");
            System.out.println("4. View Summary");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addVehicle();
                case 2 -> addMaintenance();
                case 3 -> addUsage();
                case 4 -> viewSummary();
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }

    private static void addVehicle() {
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Type (Sedan/SUV/Truck): ");
        String type = scanner.nextLine();

        controller.addVehicle(model, make, year, type);
        System.out.println("Vehicle added.");
    }

    private static void addMaintenance() {
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        controller.addMaintenance(date, desc, cost);
        System.out.println("Maintenance record added.");
    }

    private static void addUsage() {
        System.out.print("Start Date (YYYY-MM-DD): ");
        String start = scanner.nextLine();
        System.out.print("End Date (YYYY-MM-DD): ");
        String end = scanner.nextLine();
        System.out.print("Kilometers Driven: ");
        int km = Integer.parseInt(scanner.nextLine());

        controller.addUsage(start, end, km);
        System.out.println("Usage log added.");
    }

    private static void viewSummary() {
        System.out.println("\nView which summary?");
        System.out.println("1. Vehicles");
        System.out.println("2. Maintenance Records");
        System.out.println("3. Usage Logs");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> controller.getVehicles().forEach(System.out::println);
            case 2 -> controller.getMaintenanceRecords().forEach(System.out::println);
            case 3 -> controller.getUsageLogs().forEach(System.out::println);
            default -> System.out.println("Invalid choice.");
        }
    }
}