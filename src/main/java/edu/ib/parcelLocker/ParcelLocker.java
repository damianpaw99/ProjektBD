package edu.ib.parcelLocker;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParcelLocker {

    private IntegerProperty id;
    private StringProperty address;
    private StringProperty availability;

    public ParcelLocker(){
        id=new SimpleIntegerProperty();
        address=new SimpleStringProperty();
        availability=new SimpleStringProperty();
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getAvailability() {
        return availability.get();
    }

    public StringProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability.set(availability);
    }
}
