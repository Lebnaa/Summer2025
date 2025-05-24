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

package controller;

import model.*;
import java.util.*;

public class DeviceController {
    private final ElectronicDevice[] devices;

    public DeviceController(ElectronicDevice[] devices) {
        this.devices = devices;
    }

    public ElectronicDevice getMostExpensiveDevice() {
        ElectronicDevice max = devices[0];
        for (ElectronicDevice d : devices) {
            if (d.getPrice() > max.getPrice()) {
                max = d;
            }
        }
        return max;
    }

    public ElectronicDevice[] getDevicesSortedByPriceDesc() {
        Arrays.sort(devices, Collections.reverseOrder());
        return devices;
    }

    public void showFunctionalityByCategory(String category) {
        for (ElectronicDevice device : devices) {
            if (device.getClass().getSuperclass().getSimpleName().equalsIgnoreCase(category)) {
                System.out.println(device + ": " + device.getFunctionality());
            }
        }
    }

    public ElectronicDevice[] getAllDevices() {
        return devices;
    }
}
