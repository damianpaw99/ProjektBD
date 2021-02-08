package edu.ib.parcelHistory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class describing parcel data from parcels_history view from database
 */
public class ParcelHistory {
    /**
     * Parcel id
     */
    private IntegerProperty parcelId;
    /**
     * Parcel date of getting state
     */
    private StringProperty date;
    /**
     * Parcel state
     */
    private StringProperty state;

    /**
     * Default constructor
     */
    public ParcelHistory() {
        date = new SimpleStringProperty();
        parcelId = new SimpleIntegerProperty();
        state = new SimpleStringProperty();
    }

    /**
     * Date getter as String
     *
     * @return Date as String
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Property of date getter
     *
     * @return Property of date
     */
    public StringProperty dateProperty() {
        return date;
    }

    /**
     * String value of date setter
     *
     * @param date Date String
     */
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * Id value getter
     *
     * @return Parcel id value
     */
    public int getParcelId() {
        return parcelId.get();
    }

    /**
     * Id property getter
     *
     * @return Id property
     */
    public IntegerProperty parcelIdProperty() {
        return parcelId;
    }

    /**
     * Id value setter
     *
     * @param parcelId Id value
     */
    public void setParcelId(int parcelId) {
        this.parcelId.set(parcelId);
    }

    /**
     * State getter
     *
     * @return State
     */
    public String getState() {
        return state.get();
    }

    /**
     * Property of state getter
     *
     * @return Property of state
     */
    public StringProperty stateProperty() {
        return state;
    }

    /**
     * Sataet value setter
     *
     * @param state State value
     */
    public void setState(String state) {
        this.state.set(state);
    }
}
