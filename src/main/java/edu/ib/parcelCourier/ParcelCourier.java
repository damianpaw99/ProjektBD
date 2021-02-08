package edu.ib.parcelCourier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class describing courier parcel
 */
public class ParcelCourier {
    /**
     * Parcel id
     */
    private IntegerProperty parcelId;
    /**
     * Parcel state
     */
    private StringProperty state;
    /**
     * Outbox address
     */
    private StringProperty outboxAddress;
    /**
     * Inbox address
     */
    private StringProperty inboxAddress;
    /**
     * Date of getting state of parcel
     */
    private StringProperty date;

    /**
     * Default constructor
     */
    public ParcelCourier() {
        parcelId = new SimpleIntegerProperty();
        state = new SimpleStringProperty();
        outboxAddress = new SimpleStringProperty();
        inboxAddress = new SimpleStringProperty();
        date = new SimpleStringProperty();
    }

    /**
     * Strng value of date getter
     *
     * @return String value of date
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Date property getter
     *
     * @return Date property
     */
    public StringProperty dateProperty() {
        return date;
    }

    /**
     * String date setter
     *
     * @param date String date
     */
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * Parcel id value getter
     *
     * @return Parcel id value
     */
    public int getParcelId() {
        return parcelId.get();
    }

    /**
     * Parcel id property getter
     *
     * @return Parcel id property
     */
    public IntegerProperty parcelIdProperty() {
        return parcelId;
    }

    /**
     * Parcel id value setter
     *
     * @param parcelId Parcel id value
     */
    public void setParcelId(int parcelId) {
        this.parcelId.set(parcelId);
    }

    /**
     * Parcel status value getter
     *
     * @return Parcel status value
     */
    public String getState() {
        return state.get();
    }

    /**
     * Parcel status property getter
     *
     * @return Parcel status property
     */
    public StringProperty stateProperty() {
        return state;
    }

    /**
     * Parcel status value setter
     *
     * @param state Parcel state value
     */
    public void setState(String state) {
        this.state.set(state);
    }

    /**
     * OutboxAddress value getter
     *
     * @return Outbox value
     */
    public String getOutboxAddress() {
        return outboxAddress.get();
    }

    /**
     * OutboxAddress property getter
     *
     * @return OutboxAddress propertt
     */
    public StringProperty outboxAddressProperty() {
        return outboxAddress;
    }

    /**
     * OutboxAddress value setter
     *
     * @param outboxAddress OutboxAddress value
     */
    public void setOutboxAddress(String outboxAddress) {
        this.outboxAddress.set(outboxAddress);
    }

    /**
     * InboxAddress value getter
     *
     * @return InboxAddress value
     */
    public String getInboxAddress() {
        return inboxAddress.get();
    }

    /**
     * InboxAddress property getter
     *
     * @return InboxAddress property getter
     */
    public StringProperty inboxAddressProperty() {
        return inboxAddress;
    }

    /**
     * InboxAddress value setter
     *
     * @param inboxAddress InboxAddress value
     */
    public void setInboxAddress(String inboxAddress) {
        this.inboxAddress.set(inboxAddress);
    }
}
