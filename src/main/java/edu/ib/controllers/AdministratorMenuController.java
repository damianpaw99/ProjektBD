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

public class AdministratorMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnParcels;

    @FXML
    private Button btnIncome;

    @FXML
    private Button btnMachines;

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

    @FXML
    void income(ActionEvent event) {

    }

    @FXML
    void machines(ActionEvent event) {

    }

    @FXML
    void parcels(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAddEmployee != null : "fx:id=\"btnAddEmployee\" was not injected: check your FXML file 'administrator_menu.fxml'.";
        assert btnParcels != null : "fx:id=\"btnParcels\" was not injected: check your FXML file 'administrator_menu.fxml'.";
        assert btnIncome != null : "fx:id=\"btnIncome\" was not injected: check your FXML file 'administrator_menu.fxml'.";
        assert btnMachines != null : "fx:id=\"btnMachines\" was not injected: check your FXML file 'administrator_menu.fxml'.";

    }
}
