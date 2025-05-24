/*
 **********************************************
 Workshop 1
 Course: OOP - Semester
 Last Name: Noori
 First Name: Lebna
 ID: 157672205
 Section: NAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: 2025-05-23
 **********************************************
*/


import model.*;
import controller.DeviceController;
import view.DeviceView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--: Requirement 1 :--");
        System.out.print("Enter the price for SmartPhone: ");
        double phonePrice = input.nextDouble();

        System.out.print("Enter the price for Tablet: ");
        double tabletPrice = input.nextDouble();

        System.out.print("Enter the price for GamingConsole: ");
        double gamePrice = input.nextDouble();

        System.out.print("Enter the price for SmartTV: ");
        double tvPrice = input.nextDouble();

        System.out.print("Enter the price for SmartSpeaker: ");
        double speakerPrice = input.nextDouble();

        ElectronicDevice[] devices = {
                new SmartPhone(phonePrice),
                new Tablet(tabletPrice),
                new GamingConsole(gamePrice),
                new SmartTV(tvPrice),
                new SmartSpeaker(speakerPrice)
        };

        DeviceController controller = new DeviceController(devices);
        DeviceView view = new DeviceView();

        System.out.println("--: Requirement 2 :--");
        ElectronicDevice mostExpensive = controller.getMostExpensiveDevice();
        view.displayDeviceDetails(mostExpensive);

        System.out.println("--: Requirement 3 :--");
        view.displayDevicesInOrder(controller.getDevicesSortedByPriceDesc());

        System.out.println("--: Requirement 4 :--");
        input.nextLine(); // clear buffer
        System.out.print("Enter a device category (CommunicationDevices, EntertainmentDevices, UtilityDevices): ");
        String category = input.nextLine();
        view.displayCategoryHeader(category);
        controller.showFunctionalityByCategory(category);

        input.close();
    }
}
