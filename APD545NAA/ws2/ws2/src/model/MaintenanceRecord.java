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

package model;

public class MaintenanceRecord {
    private String date;
    private String description;
    private double cost;

    public MaintenanceRecord(String date, String description, double cost) {
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s - %s ($%.2f)", date, description, cost);
    }
}