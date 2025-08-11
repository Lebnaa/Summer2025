package seneca.summer25.apdfinal.Model;

import javafx.beans.property.*;

public class Guest {
    private final IntegerProperty guestID = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty feedback = new SimpleStringProperty();
    private final StringProperty checkInDate = new SimpleStringProperty();
    private final StringProperty checkOutDate = new SimpleStringProperty();
    private final StringProperty roomType = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty("Confirmed");  // or "Cancelled", "Checked Out"

    // Getters and Setters
    public int getGuestID() { return guestID.get(); }
    public void setGuestID(int value) { guestID.set(value); }
    public IntegerProperty guestIDProperty() { return guestID; }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public String getPhoneNumber() { return phoneNumber.get(); }
    public void setPhoneNumber(String value) { phoneNumber.set(value); }
    public StringProperty phoneNumberProperty() { return phoneNumber; }

    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    public String getAddress() { return address.get(); }
    public void setAddress(String value) { address.set(value); }
    public StringProperty addressProperty() { return address; }

    public String getFeedback() { return feedback.get(); }
    public void setFeedback(String value) { feedback.set(value); }
    public StringProperty feedbackProperty() { return feedback; }

    public String getCheckInDate() { return checkInDate.get(); }
    public void setCheckInDate(String value) { checkInDate.set(value); }
    public StringProperty checkInDateProperty() { return checkInDate; }

    public String getCheckOutDate() { return checkOutDate.get(); }
    public void setCheckOutDate(String value) { checkOutDate.set(value); }
    public StringProperty checkOutDateProperty() { return checkOutDate; }

    public String getRoomType() { return roomType.get(); }
    public void setRoomType(String value) { roomType.set(value); }
    public StringProperty roomTypeProperty() { return roomType; }

    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }
}
