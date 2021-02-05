package edu.ib.parcel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Parcel {
    private IntegerProperty id;
    private IntegerProperty confirmationNumber;
    private StringProperty size;
    private StringProperty recipientName;
    private StringProperty recipientSurname;
    private IntegerProperty recipientNumber;
    private StringProperty recipientEmail;

    public Parcel(){
        id=new SimpleIntegerProperty();
        confirmationNumber=new SimpleIntegerProperty();
        size=new SimpleStringProperty();
        recipientName=new SimpleStringProperty();
        recipientSurname=new SimpleStringProperty();
        recipientNumber=new SimpleIntegerProperty();
        recipientEmail=new SimpleStringProperty();
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

    public int getConfirmationNumber() {
        return confirmationNumber.get();
    }

    public IntegerProperty confirmationNumberProperty() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber.set(confirmationNumber);
    }

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getRecipientName() {
        return recipientName.get();
    }

    public StringProperty recipientNameProperty() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName.set(recipientName);
    }

    public String getRecipientSurname() {
        return recipientSurname.get();
    }

    public StringProperty recipientSurnameProperty() {
        return recipientSurname;
    }

    public void setRecipientSurname(String recipientSurname) {
        this.recipientSurname.set(recipientSurname);
    }

    public int getRecipientNumber() {
        return recipientNumber.get();
    }

    public IntegerProperty recipientNumberProperty() {
        return recipientNumber;
    }

    public void setRecipientNumber(int recipientNumber) {
        this.recipientNumber.set(recipientNumber);
    }

    public String getRecipientEmail() {
        return recipientEmail.get();
    }

    public StringProperty recipientEmailProperty() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail.set(recipientEmail);
    }
}
