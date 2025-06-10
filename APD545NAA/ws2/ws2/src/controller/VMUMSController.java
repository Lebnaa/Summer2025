package controller;

import model.Vehicle;
import model.MaintenanceRecord;
import model.UsageLog;

import java.util.ArrayList;
import java.util.List;

public class VMUMSController {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<MaintenanceRecord> maintenanceRecords = new ArrayList<>();
    private final List<UsageLog> usageLogs = new ArrayList<>();

    public void addVehicle(String model, String make, int year, String type) {
        vehicles.add(new Vehicle(model, make, year, type));
    }

    public void addMaintenance(String date, String description, double cost) {
        maintenanceRecords.add(new MaintenanceRecord(date, description, cost));
    }

    public void addUsage(String startDate, String endDate, int km) {
        usageLogs.add(new UsageLog(startDate, endDate, km));
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<MaintenanceRecord> getMaintenanceRecords() {
        return maintenanceRecords;
    }

    public List<UsageLog> getUsageLogs() {
        return usageLogs;
    }
}