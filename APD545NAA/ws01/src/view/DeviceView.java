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


package view;

import model.ElectronicDevice;

public class DeviceView {
    public void displayDeviceDetails(ElectronicDevice device) {
        System.out.println("The most expensive device is: " + device);
        System.out.println(device + "’s cost is: $" + device.getPrice());
        System.out.println(device + " is operated: " + device.howToOperate());
        System.out.println(device + " maintenance: " + device.howToMaintain());
        System.out.println(device + " function type: " + device.getFunctionType());
    }

    public void displayDevicesInOrder(ElectronicDevice[] devices) {
        System.out.println("Devices in Descending Order of Price:");
        for (ElectronicDevice device : devices) {
            System.out.println(device);
        }
    }

    public void displayCategoryHeader(String category) {
        System.out.println("Functionality of " + category + ":");
    }
}