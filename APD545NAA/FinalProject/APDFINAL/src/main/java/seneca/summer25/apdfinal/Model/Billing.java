package seneca.summer25.apdfinal.Model;

import javafx.beans.property.*;

public class Billing {
    private final IntegerProperty billID = new SimpleIntegerProperty();
    private final IntegerProperty reservationID = new SimpleIntegerProperty();
    private final DoubleProperty amount = new SimpleDoubleProperty();
    private final DoubleProperty tax = new SimpleDoubleProperty();
    private final DoubleProperty totalAmount = new SimpleDoubleProperty();
    private final DoubleProperty discount = new SimpleDoubleProperty();

    public int getBillID() { return billID.get(); }
    public void setBillID(int value) { billID.set(value); }
    public IntegerProperty billIDProperty() { return billID; }

    public int getReservationID() { return reservationID.get(); }
    public void setReservationID(int value) { reservationID.set(value); }
    public IntegerProperty reservationIDProperty() { return reservationID; }

    public double getAmount() { return amount.get(); }
    public void setAmount(double value) { amount.set(value); }
    public DoubleProperty amountProperty() { return amount; }

    public double getTax() { return tax.get(); }
    public void setTax(double value) { tax.set(value); }
    public DoubleProperty taxProperty() { return tax; }

    public double getTotalAmount() { return totalAmount.get(); }
    public void setTotalAmount(double value) { totalAmount.set(value); }
    public DoubleProperty totalAmountProperty() { return totalAmount; }

    public double getDiscount() { return discount.get(); }
    public void setDiscount(double value) { discount.set(value); }
    public DoubleProperty discountProperty() { return discount; }
}
