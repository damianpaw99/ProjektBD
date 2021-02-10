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
 * class that handles administrator menu screen (administrator_menu.fxml)
 */
public class AdministratorMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * button to go to new employee creation screen
     */
    @FXML
    private Button btnAddEmployee;

    /**
     * button to go to all parcels history screen
     */
    @FXML
    private Button btnParcels;

    /**
     * button to go to income informations screen
     */
    @FXML
    private Button btnIncome;

    /**
     * button to go to machines statistics screen
     */
    @FXML
    private Button btnMachines;

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
            e.getStackTrace();
        }
    }

    /**
     * method to go to income informations screen
     *
     * @param event information about event
     */
    @FXML
    void income(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/income.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * method to go to machines statistics screen
     *
     * @param event information about event
     */
    @FXML
    void machines(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/machines.fxml"));
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * method to go to parcels history screen
     *
     * @param event information about event
     */
    @FXML
    void parcels(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/parcels.fxml"));
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
        assert btnAddEmployee != null : "fx:id=\"btnAddEmployee\" was not injected: check your FXML file 'administrator_menu.fxml'.";
        assert btnParcels != null : "fx:id=\"btnParcels\" was not injected: check your FXML file 'administrator_menu.fxml'.";
        assert btnIncome != null : "fx:id=\"btnIncome\" was not injected: check your FXML file 'administrator_menu.fxml'.";
        assert btnMachines != null : "fx:id=\"btnMachines\" was not injected: check your FXML file 'administrator_menu.fxml'.";

    }
}
