package edu.ib.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.ib.parcelAdministrator.ParcelAdministrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ParcelsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private Text txtMessage;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnlogout;

    @FXML
    private TableView tbParcels;

    @FXML
    private TableColumn<ParcelAdministrator, String> tbRowSender;

    @FXML
    private TableColumn<ParcelAdministrator, Integer> tbRowParcelId;

    @FXML
    private TableColumn<ParcelAdministrator, String> tbRowDate;

    @FXML
    private TableColumn<ParcelAdministrator, String> tbRowState;

    @FXML
    private TextField etxtSearch;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'parcels.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'parcels.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'parcels.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'parcels.fxml'.";
        assert btnlogout != null : "fx:id=\"btnlogout\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbParcels != null : "fx:id=\"tbParcels\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowSender != null : "fx:id=\"tbRowSender\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowParcelId != null : "fx:id=\"tbRowParcelId\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowDate != null : "fx:id=\"tbRowDate\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowState != null : "fx:id=\"tbRowState\" was not injected: check your FXML file 'parcels.fxml'.";
        assert etxtSearch != null : "fx:id=\"etxtSearch\" was not injected: check your FXML file 'parcels.fxml'.";

    }
}
