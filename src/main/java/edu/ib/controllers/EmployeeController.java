package edu.ib.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField etxtParcelId;

    @FXML
    private Button btnGetSendParcel;

    @FXML
    private Button btnLeaveParcel;

    @FXML
    private Button btnMissedParcel;

    @FXML
    private TableView<?> tbParcel;

    @FXML
    private TextField etxtSearch;

    @FXML
    void getSendParcel(ActionEvent event) {

    }

    @FXML
    void leaveParcel(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void toggleButtons(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'employee.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'employee.fxml'.";
        assert etxtParcelId != null : "fx:id=\"etxtParcelId\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnGetSendParcel != null : "fx:id=\"btnGetSendParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnLeaveParcel != null : "fx:id=\"btnLeaveParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert btnMissedParcel != null : "fx:id=\"btnMissedParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert tbParcel != null : "fx:id=\"tbParcel\" was not injected: check your FXML file 'employee.fxml'.";
        assert etxtSearch != null : "fx:id=\"etxtSearch\" was not injected: check your FXML file 'employee.fxml'.";

    }
}
