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

public class IncomeController {

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
    private Button btnLogout;

    @FXML
    private TableView<?> tbIncome;

    @FXML
    private TableColumn<?, ?> tbRowDate;

    @FXML
    private TableColumn<?, ?> tbRowIncome;

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
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'income.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'income.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'income.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'income.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'income.fxml'.";
        assert tbIncome != null : "fx:id=\"tbIncome\" was not injected: check your FXML file 'income.fxml'.";
        assert tbRowDate != null : "fx:id=\"tbRowDate\" was not injected: check your FXML file 'income.fxml'.";
        assert tbRowIncome != null : "fx:id=\"tbRowIncome\" was not injected: check your FXML file 'income.fxml'.";
        assert etxtSearch != null : "fx:id=\"etxtSearch\" was not injected: check your FXML file 'income.fxml'.";

    }
}
