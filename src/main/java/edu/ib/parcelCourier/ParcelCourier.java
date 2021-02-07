package edu.ib.parcelCourier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParcelCourier {

    private IntegerProperty parcelId;
    private StringProperty state;
    private StringProperty outboxAddress;
    private StringProperty inboxAddress;
    private StringProperty date;

    public ParcelCourier() {
        parcelId = new SimpleIntegerProperty();
        state = new SimpleStringProperty();
        outboxAddress = new SimpleStringProperty();
        inboxAddress = new SimpleStringProperty();
        date=new SimpleStringProperty();
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getParcelId() {
        return parcelId.get();
    }

    public IntegerProperty parcelIdProperty() {
        return parcelId;
    }

    public void setParcelId(int parcelId) {
        this.parcelId.set(parcelId);
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getOutboxAddress() {
        return outboxAddress.get();
    }

    public StringProperty outboxAddressProperty() {
        return outboxAddress;
    }

    public void setOutboxAddress(String outboxAddress) {
        this.outboxAddress.set(outboxAddress);
    }

    public String getInboxAddress() {
        return inboxAddress.get();
    }

    public StringProperty inboxAddressProperty() {
        return inboxAddress;
    }

    public void setInboxAddress(String inboxAddress) {
        this.inboxAddress.set(inboxAddress);
    }
}
