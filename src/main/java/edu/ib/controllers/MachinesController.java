package edu.ib.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MachinesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private Text etxtMessage;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnLogout;

    @FXML
    private TableView<?> tbMachine;

    @FXML
    private TableColumn<?, ?> tbRowID;

    @FXML
    private TableColumn<?, ?> tbRowAddress;

    @FXML
    private TableColumn<?, ?> tbRowDate;

    @FXML
    private TableColumn<?, ?> tbRowToPickup;

    @FXML
    private TableColumn<?, ?> tbRowPickedup;

    @FXML
    private TableColumn<?, ?> tbRowToPickupClient;

    @FXML
    private TableColumn<?, ?> tbRowMissed;

    @FXML
    private TextField etxtSearchID;

    @FXML
    private TextField etxtSearchDate;

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
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtMessage != null : "fx:id=\"etxtMessage\" was not injected: check your FXML file 'machines.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'machines.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbMachine != null : "fx:id=\"tbMachine\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowID != null : "fx:id=\"tbRowID\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowAddress != null : "fx:id=\"tbRowAddress\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowDate != null : "fx:id=\"tbRowDate\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowToPickup != null : "fx:id=\"tbRowToPickup\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowPickedup != null : "fx:id=\"tbRowPickedup\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowToPickupClient != null : "fx:id=\"tbRowToPickupClient\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowMissed != null : "fx:id=\"tbRowMissed\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtSearchID != null : "fx:id=\"etxtSearchID\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtSearchDate != null : "fx:id=\"etxtSearchDate\" was not injected: check your FXML file 'machines.fxml'.";

    }
}
