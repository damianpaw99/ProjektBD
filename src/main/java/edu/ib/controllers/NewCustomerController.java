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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtName;

    @FXML
    private TextField etxtSurname;

    @FXML
    private TextField etxtEmail;

    @FXML
    private TextField etxtPhone;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private PasswordField etxtRepeatPassword;

    @FXML
    private Text txtMessage;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnBack;

    @FXML
    void addClient(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
            stage.setScene(new Scene(root,1000,800));
        } catch(Exception e){
            e.getStackTrace();
        }
    }

    @FXML
    void back(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/main_menu.fxml"));
            stage.setScene(new Scene(root,1000,800));
        } catch(Exception e){
            e.getStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert etxtName != null : "fx:id=\"etxtName\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert etxtSurname != null : "fx:id=\"etxtSurname\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert etxtEmail != null : "fx:id=\"etxtEmail\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert etxtPhone != null : "fx:id=\"etxtPhone\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert etxtRepeatPassword != null : "fx:id=\"etxtRepeatPassword\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert btnAddClient != null : "fx:id=\"btnAddClient\" was not injected: check your FXML file 'new_customer.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'new_customer.fxml'.";
    }
}
