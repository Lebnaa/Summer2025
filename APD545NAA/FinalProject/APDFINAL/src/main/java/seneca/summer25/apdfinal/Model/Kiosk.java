package seneca.summer25.apdfinal.Model;

import javafx.beans.property.*;

public class Kiosk {
    private final IntegerProperty kioskID = new SimpleIntegerProperty();
    private final StringProperty location = new SimpleStringProperty();

    public int getKioskID() { return kioskID.get(); }
    public void setKioskID(int value) { kioskID.set(value); }
    public IntegerProperty kioskIDProperty() { return kioskID; }

    public String getLocation() { return location.get(); }
    public void setLocation(String value) { location.set(value); }
    public StringProperty locationProperty() { return location; }
}
