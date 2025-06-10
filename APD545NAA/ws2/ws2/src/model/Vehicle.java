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

public class Vehicle {
    private String model;
    private String make;
    private int year;
    private String type;

    public Vehicle(String model, String make, int year, String type) {
        this.model = model;
        this.make = make;
        this.year = year;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d) - Type: %s", make, model, year, type);
    }
}