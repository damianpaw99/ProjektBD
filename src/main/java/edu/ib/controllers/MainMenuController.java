package edu.ib.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNewClient;

    @FXML
    private Button btnNewEmployee;

    @FXML
    private Button btnLogClient;

    @FXML
    private Button btnLogEmployee;

    @FXML
    private Button btnGetParcel;

    @FXML
    void addEmployee(ActionEvent event) {

    }

    @FXML
    void addNewClient(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/new_customer.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    void logClient(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/customer.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void logEmployee(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/employee.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnNewClient != null : "fx:id=\"btnNewClient\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnNewEmployee != null : "fx:id=\"btnNewEmployee\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnLogClient != null : "fx:id=\"btnLogClient\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnLogEmployee != null : "fx:id=\"btnLogEmployee\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnGetParcel != null : "fx:id=\"btnGetParcel\" was not injected: check your FXML file 'main_menu.fxml'.";
    }
}
