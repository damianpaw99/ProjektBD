package edu.ib.parcelLocker;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class describing one parcel machine data to show to customer
 */
public class ParcelLocker {
    /**
     * Id of parcel machine
     */
    private IntegerProperty id;
    /**
     * Address of parcel machine
     */
    private StringProperty address;

    /**
     * Default constructor
     */
    public ParcelLocker() {
        id = new SimpleIntegerProperty();
        address = new SimpleStringProperty();
    }

    /**
     * Id value getter
     *
     * @return Id as int
     */
    public int getId() {
        return id.get();
    }

    /**
     * Id property getter
     *
     * @return Id property
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Id setter
     *
     * @param id Id number
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Address value getter
     *
     * @return Address value
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * Address property getter
     *
     * @return Address property
     */
    public StringProperty addressProperty() {
        return address;
    }

    /**
     * Method setting address
     *
     * @param address Address as String
     */
    public void setAddress(String address) {
        this.address.set(address);
    }
}
