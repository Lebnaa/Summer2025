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

public class SmartTV extends EntertainmentDevices implements IDeviceOperable, IDeviceMaintanable {
    public SmartTV(double price) {
        super("SmartTV", price, "Visual entertainment");
    }

    public String howToOperate() {
        return "By using remote control";
    }

    public String howToMaintain() {
        return "Update firmware, clean screen";
    }


    @Override
    public String getFunctionType() {
        return "Visual entertainment";
    }

    public String getFunctionality() {
        return "Streaming and media viewing";
    }
}
