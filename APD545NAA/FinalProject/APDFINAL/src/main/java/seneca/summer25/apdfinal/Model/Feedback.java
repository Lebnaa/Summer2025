package seneca.summer25.apdfinal.Model;

import javafx.beans.property.*;

public class Feedback {
    private final IntegerProperty feedbackID = new SimpleIntegerProperty();
    private final IntegerProperty guestID = new SimpleIntegerProperty();
    private final IntegerProperty reservationID = new SimpleIntegerProperty();
    private final StringProperty comments = new SimpleStringProperty();
    private final IntegerProperty rating = new SimpleIntegerProperty();

    public int getFeedbackID() { return feedbackID.get(); }
    public void setFeedbackID(int value) { feedbackID.set(value); }
    public IntegerProperty feedbackIDProperty() { return feedbackID; }

    public int getGuestID() { return guestID.get(); }
    public void setGuestID(int value) { guestID.set(value); }
    public IntegerProperty guestIDProperty() { return guestID; }

    public int getReservationID() { return reservationID.get(); }
    public void setReservationID(int value) { reservationID.set(value); }
    public IntegerProperty reservationIDProperty() { return reservationID; }

    public String getComments() { return comments.get(); }
    public void setComments(String value) { comments.set(value); }
    public StringProperty commentsProperty() { return comments; }

    public int getRating() { return rating.get(); }
    public void setRating(int value) { rating.set(value); }
    public IntegerProperty ratingProperty() { return rating; }
}
