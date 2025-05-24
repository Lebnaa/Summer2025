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


package model;

public class Tablet extends CommunicationDevices implements IDeviceOperable, IDeviceMaintanable {
    public Tablet(double price) {
        super("Tablet", price, "Multi-functional");
    }

    @Override
    public String howToOperate() {
        return "By using touchscreen";
    }

    @Override
    public String howToMaintain() {
        return "Regular software updates";
    }

    @Override
    public String getFunctionality() {
        return "Larger screen communication";
    }

    @Override
    public String getFunctionType() {
        return "Multi-functional";
    }
    @Override
    public String toString() {
        return "Tablet";
    }
}
