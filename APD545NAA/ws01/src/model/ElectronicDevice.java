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

public abstract class ElectronicDevice implements Comparable<ElectronicDevice> {
    protected String name;
    protected double price;
    protected String functionType;

    public ElectronicDevice(String name, double price, String functionType) {
        this.name = name;
        this.price = price;
        this.functionType = functionType;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    public abstract String getFunctionality();
    public abstract String howToOperate();
    public abstract String howToMaintain();
    public abstract String getFunctionType();

    @Override
    public int compareTo(ElectronicDevice other) {
        return Double.compare(other.price, this.price);
    }

    @Override
    public String toString() {
        return name;
    }
}
