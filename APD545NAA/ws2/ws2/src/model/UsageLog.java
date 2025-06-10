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

public class UsageLog {
    private String startDate;
    private String endDate;
    private int kilometers;

    public UsageLog(String startDate, String endDate, int kilometers) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.kilometers = kilometers;
    }

    @Override
    public String toString() {
        return String.format("From %s to %s: %d km", startDate, endDate, kilometers);
    }
}
