package seneca.summer25.apdfinal.Model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Reservation {
    private final IntegerProperty reservationID = new SimpleIntegerProperty();
    private final IntegerProperty guestID = new SimpleIntegerProperty();
    private final IntegerProperty roomID = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> checkInDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> checkOutDate = new SimpleObjectProperty<>();
    private final IntegerProperty numberOfGuests = new SimpleIntegerProperty();
    private final StringProperty status = new SimpleStringProperty();

    // Getters and setters
    public int getReservationID() { return reservationID.get(); }
    public void setReservationID(int value) { reservationID.set(value); }
    public IntegerProperty reservationIDProperty() { return reservationID; }

    public int getGuestID() { return guestID.get(); }
    public void setGuestID(int value) { guestID.set(value); }
    public IntegerProperty guestIDProperty() { return guestID; }

    public int getRoomID() { return roomID.get(); }
    public void setRoomID(int value) { roomID.set(value); }
    public IntegerProperty roomIDProperty() { return roomID; }

    public LocalDate getCheckInDate() { return checkInDate.get(); }
    public void setCheckInDate(LocalDate value) { checkInDate.set(value); }
    public ObjectProperty<LocalDate> checkInDateProperty() { return checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate.get(); }
    public void setCheckOutDate(LocalDate value) { checkOutDate.set(value); }
    public ObjectProperty<LocalDate> checkOutDateProperty() { return checkOutDate; }

    public int getNumberOfGuests() { return numberOfGuests.get(); }
    public void setNumberOfGuests(int value) { numberOfGuests.set(value); }
    public IntegerProperty numberOfGuestsProperty() { return numberOfGuests; }

    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }
}
