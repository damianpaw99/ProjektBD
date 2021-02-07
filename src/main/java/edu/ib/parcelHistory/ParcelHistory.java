package edu.ib.parcelHistory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParcelHistory {

    private StringProperty date;
    private IntegerProperty parcelId;
    private StringProperty state;
    private IntegerProperty code;


    public ParcelHistory(){
        date =new SimpleStringProperty();
        parcelId=new SimpleIntegerProperty();
        state=new SimpleStringProperty();
        code=new SimpleIntegerProperty();
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

    public int getCode() {
        return code.get();
    }

    public IntegerProperty codeProperty() {
        return code;
    }

    public void setCode(int code) {
        this.code.set(code);
    }
}
