package seneca.summer25.apdfinal.Model;

import javafx.beans.property.*;

public class Admin {
    private final IntegerProperty adminID = new SimpleIntegerProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    public int getAdminID() { return adminID.get(); }
    public void setAdminID(int value) { adminID.set(value); }
    public IntegerProperty adminIDProperty() { return adminID; }

    public String getUsername() { return username.get(); }
    public void setUsername(String value) { username.set(value); }
    public StringProperty usernameProperty() { return username; }

    public String getPassword() { return password.get(); }
    public void setPassword(String value) { password.set(value); }
    public StringProperty passwordProperty() { return password; }
}
