package edu.ib.machineStats;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class describing parcel machine stats
 */
public class Machine {
    /**
     * Parcel machine id
     */
    private IntegerProperty id;
    /**
     * Parcel machine address
     */
    private StringProperty address;
    /**
     * Date of data
     */
    private StringProperty date;
    /**
     * Number of parcels send
     */
    private IntegerProperty send;
    /**
     * Number of parcels picked up by couriers
     */
    private IntegerProperty pickupCourier;
    /**
     * Number of parcels left by courier to pickup by client
     */
    private IntegerProperty readyToPickup;
    /**
     * Number of parcels picked up by client
     */
    private IntegerProperty pickedup;
    /**
     * Number of picked up missed parcels by courier
     */
    private IntegerProperty missed;

    /**
     * Default constructor
     */
    public Machine(){
        id=new SimpleIntegerProperty();
        address=new SimpleStringProperty();
        date=new SimpleStringProperty();
        send=new SimpleIntegerProperty();
        pickupCourier=new SimpleIntegerProperty();
        readyToPickup=new SimpleIntegerProperty();
        pickedup=new SimpleIntegerProperty();
        missed=new SimpleIntegerProperty();
    }

    /**
     * Date value getter
     * @return Date value
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Date property getter
     * @return Date property
     */
    public StringProperty dateProperty() {
        return date;
    }

    /**
     * Date value setter
     * @param date Date value
     */
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * Id value getter
     * @return Id value
     */
    public int getId() {
        return id.get();
    }

    /**
     * Id property getter
     * @return Id property
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Id value setter
     * @param id Id value
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Address value getter
     * @return Address value
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * Address property getter
     * @return Address property
     */
    public StringProperty addressProperty() {
        return address;
    }

    /**
     * Address value setter
     * @param address Address value
     */
    public void setAddress(String address) {
        this.address.set(address);
    }

    /**
     * Number of parcels send getter
     * @return Number of parcels send
     */
    public int getSend() {
        return send.get();
    }

    /**
     * Property of parcels send getter
     * @return Property of parcels send
     */
    public IntegerProperty sendProperty() {
        return send;
    }

    /**
     * Number of parcels send setter
     * @param send Number of parcels send
     */
    public void setSend(int send) {
        this.send.set(send);
    }

    /**
     * Number of parcels picked up by courier getter
     * @return Number of parcels picked up by courier
     */
    public int getPickupCourier() {
        return pickupCourier.get();
    }
    /**
     * Property of parcels picked up by courier getter
     * @return Property of parcels picked up by courier
     */
    public IntegerProperty pickupCourierProperty() {
        return pickupCourier;
    }
    /**
     * Number of parcels picked up by courier setter
     * @param pickupCourier Number of parcels picked up by courier
     */
    public void setPickupCourier(int pickupCourier) {
        this.pickupCourier.set(pickupCourier);
    }
    /**
     * Number of parcels ready to be picked up by client getter
     * @return Number of parcels ready to be picked up by client
     */
    public int getReadyToPickup() {
        return readyToPickup.get();
    }
    /**
     * Property of parcels ready to be picked up by client getter
     * @return Property of parcels ready to be picked up by client
     */
    public IntegerProperty readyToPickupProperty() {
        return readyToPickup;
    }
    /**
     * Number of parcels ready to be picked up by client setter
     * @param readyToPickup  Number of parcels ready to be picked up by client
     */
    public void setReadyToPickup(int readyToPickup) {
        this.readyToPickup.set(readyToPickup);
    }
    /**
     * Number of parcels picked up by client getter
     * @return Number of parcels picked up by client
     */
    public int getPickedup() {
        return pickedup.get();
    }
    /**
     * Property of parcels picked up by client getter
     * @return Property of parcels picked up by client
     */
    public IntegerProperty pickedupProperty() {
        return pickedup;
    }
    /**
     * Number of parcels picked up by client setter
     * @param pickedup Number of parcels picked up by client
     */
    public void setPickedup(int pickedup) {
        this.pickedup.set(pickedup);
    }

    /**
     * Number of missed parcels picked up by courier getter
     * @return Number of missed parcels picked up by courier
     */
    public int getMissed() {
        return missed.get();
    }
    /**
     * Property of missed parcels picked up by courier getter
     * @return Property of missed parcels picked up by courier
     */
    public IntegerProperty missedProperty() {
        return missed;
    }
    /**
     * Number of missed parcels picked up by courier setter
     * @param missed  Number of missed parcels picked up by courier
     */
    public void setMissed(int missed) {
        this.missed.set(missed);
    }
}
