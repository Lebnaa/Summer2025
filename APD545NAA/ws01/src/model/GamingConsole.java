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

public class GamingConsole extends EntertainmentDevices implements IDeviceOperable, IDeviceMaintanable {
    public GamingConsole(double price) {
        super("GamingConsole", price, "Interactive entertainment");
    }

    @Override
    public String howToOperate() {
        return "By using game controllers";
    }

    @Override
    public String howToMaintain() {
        return "Clean vents, software updates";
    }

    @Override
    public String getFunctionality() {
        return "Video gaming";
    }

    @Override
    public String getFunctionType() {
        return "Interactive entertainment";
    }
    @Override
    public String toString() {
        return "GamingConsole";
    }
}
