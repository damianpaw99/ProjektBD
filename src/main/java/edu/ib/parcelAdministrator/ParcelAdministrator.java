package edu.ib.parcelAdministrator;

import edu.ib.parcelHistory.ParcelHistory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class describing parcel data from parcels_history view from database
 */
public class ParcelAdministrator extends ParcelHistory {
    /**
     * Customer Login
     */
    private StringProperty clientLogin;

    /**
     * Default constructor
     */
    public ParcelAdministrator(){
        super();
        clientLogin=new SimpleStringProperty();
    }

    /**
     * Client login value getter
     * @return Client login value
     */
    public String getClientLogin() {
        return clientLogin.get();
    }

    /**
     * Client login property getter
     * @return Client value property
     */
    public StringProperty clientLoginProperty() {
        return clientLogin;
    }

    /**
     * Client login value setter
     * @param clientLogin Client login value
     */
    public void setClientLogin(String clientLogin) {
        this.clientLogin.set(clientLogin);
    }
}
