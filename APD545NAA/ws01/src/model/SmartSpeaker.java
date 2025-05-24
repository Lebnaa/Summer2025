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
public class SmartSpeaker extends UtilityDevices implements IDeviceOperable {
    public SmartSpeaker(double price) {
        super("SmartSpeaker", price, "Audio assistance");
    }

    @Override
    public String howToOperate() {
        return "By using voice commands";
    }

    @Override
    public String howToMaintain() {
        return "N/A";
    }

    @Override
    public String getFunctionality() {
        return "Voice-controlled assistance";
    }

    @Override
    public String getFunctionType() {
        return "Audio assistance";
    }

    @Override
    public String toString() {
        return "SmartSpeaker";
    }
}
