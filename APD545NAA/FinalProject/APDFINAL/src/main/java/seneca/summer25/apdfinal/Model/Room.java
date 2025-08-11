package seneca.summer25.apdfinal.Model;

import javafx.beans.property.*;

public class Room {
    private final IntegerProperty roomID = new SimpleIntegerProperty();
    private final ObjectProperty<RoomType> roomType = new SimpleObjectProperty<>();
    private final IntegerProperty numberOfBeds = new SimpleIntegerProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final StringProperty status = new SimpleStringProperty(); // AVAILABLE / BOOKED

    public int getRoomID() { return roomID.get(); }
    public void setRoomID(int value) { roomID.set(value); }
    public IntegerProperty roomIDProperty() { return roomID; }

    public RoomType getRoomType() { return roomType.get(); }
    public void setRoomType(RoomType value) { roomType.set(value); }
    public ObjectProperty<RoomType> roomTypeProperty() { return roomType; }

    public int getNumberOfBeds() { return numberOfBeds.get(); }
    public void setNumberOfBeds(int value) { numberOfBeds.set(value); }
    public IntegerProperty numberOfBedsProperty() { return numberOfBeds; }

    public double getPrice() { return price.get(); }
    public void setPrice(double value) { price.set(value); }
    public DoubleProperty priceProperty() { return price; }

    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }
}
