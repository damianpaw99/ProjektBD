package edu.ib.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * class that handles parcel pickup screen (parcels.fxml)
 */
public class PickupParcelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * editable text field to enter parcel ID
     */
    @FXML
    private TextField etxtID;

    /**
     * editable text field to enter receiving code
     */
    @FXML
    private TextField etxtCode;

    /**
     * text field with information about success or failure of acion
     */
    @FXML
    private Text txtMessage;

    /**
     * object used to connect to database
     */
    private DBUtil dbUtil;

    /**
     * method of returning to home screen
     *
     * @param event information about event
     */
    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method of receiving parcel
     *
     * @param event information about event
     */
    @FXML
    void pickupParcel(ActionEvent event) {
        try {
            String id = etxtID.getText();
            Integer.parseInt(id);
            String code = etxtCode.getText();
            Integer.parseInt(code);
            String statement = "CALL pickup_by_recipient(" + id + "," + code + ")";
            dbUtil.dbExecuteUpdate(statement);
            txtMessage.setText("Odebranie paczki zakończone sukcesem");
        } catch (NumberFormatException e) {
            txtMessage.setText("Niepoprawne dane!");
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            txtMessage.setText("Błąd połączenia lub niepoprawne dane!");
            e.printStackTrace();
        }
    }

    /**
     * method called when loading screen
     */
    @FXML
    void initialize() {
        assert etxtID != null : "fx:id=\"etxtID\" was not injected: check your FXML file 'pickup_parcel.fxml'.";
        assert etxtCode != null : "fx:id=\"etxtCode\" was not injected: check your FXML file 'pickup_parcel.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'pickup_parcel.fxml'.";
        dbUtil = new DBUtil("basic", "haslo100");
    }
}
