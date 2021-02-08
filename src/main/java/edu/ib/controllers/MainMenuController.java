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

/**
 * class that handles home screen (main_menu.fxml)
 */
public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * button to go to new customer creation screen
     */
    @FXML
    private Button btnNewClient;

    /**
     * button to go to new employee creation screen
     */
    @FXML
    private Button btnNewEmployee;

    /**
     * button to go to customer login screen
     */
    @FXML
    private Button btnLogClient;

    /**
     * button to go to employee login screen
     */
    @FXML
    private Button btnLogEmployee;

    /**
     * button to go to parcel pickup screen
     */
    @FXML
    private Button btnGetParcel;

    /**
     * method to go to new employee creation screen
     *
     * @param event information about event
     */
    @FXML
    void addEmployee(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/new_employee.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * method to go to new customer creation screen
     *
     * @param event information about event
     */
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

    /**
     * method to go to customer login screen
     *
     * @param event information about event
     */
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

    /**
     * method to go to customer login screen
     *
     * @param event information about event
     */
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

    /**
     * method to go to parcel pickup screen
     *
     * @param event information about event
     */
    @FXML
    void pickupParcel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/pickup_parcel.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * method called when loading screen
     */
    @FXML
    void initialize() {
        assert btnNewClient != null : "fx:id=\"btnNewClient\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnNewEmployee != null : "fx:id=\"btnNewEmployee\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnLogClient != null : "fx:id=\"btnLogClient\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnLogEmployee != null : "fx:id=\"btnLogEmployee\" was not injected: check your FXML file 'main_menu.fxml'.";
        assert btnGetParcel != null : "fx:id=\"btnGetParcel\" was not injected: check your FXML file 'main_menu.fxml'.";
    }
}
