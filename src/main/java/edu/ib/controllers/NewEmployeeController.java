package edu.ib.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * class that handles new employee creation screen (new_employee.fxml)
 */
public class NewEmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * editable text field to enter administrator login
     */
    @FXML
    private TextField etxtLogin;

    /**
     * editable text field to enter administrator password
     */
    @FXML
    private PasswordField etxtPassword;

    /**
     * editable text field to enter new employee login
     */
    @FXML
    private TextField etxtCourierLogin;

    /**
     * editable text field to enter new employee password
     */
    @FXML
    private PasswordField etxtCourierPassword;

    /**
     * editable text field to enter firstname of new employee
     */
    @FXML
    private TextField etxtName;

    /**
     * editable text field to enter surname of new employee
     */
    @FXML
    private TextField etxtSurname;

    /**
     * editable text field to enter phone number of new employee
     */
    @FXML
    private TextField etxtPhone;

    /**
     * text field with information about success or failure of acion
     */
    @FXML
    private Text txtMessage;

    /**
     * method of adding new employee
     *
     * @param event information about event
     */
    @FXML
    void addCourier(ActionEvent event) {
        DBUtil dbUtil = new DBUtil(etxtLogin.getText(), etxtPassword.getText());
        StringBuilder statement = new StringBuilder();
        statement.append("CALL add_courier(\"");
        statement.append(etxtCourierLogin.getText());
        statement.append("\",\"");
        try {
            statement.append(Logger.hash(etxtCourierPassword.getText()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        statement.append("\",\"");
        statement.append(etxtName.getText());
        statement.append("\",\"");
        statement.append(etxtSurname.getText());
        statement.append("\",");
        statement.append(etxtPhone.getText());
        statement.append(")");
        try {
            dbUtil.dbExecuteUpdate(statement.toString());
            txtMessage.setText("Dodano pracownika");
        } catch (ClassNotFoundException | SQLException throwables) {
            txtMessage.setText("Błąd bazy danych. Sprawdź dane logowania i format danych.");
            throwables.printStackTrace();
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
            e.printStackTrace();
        }
    }

    /**
     * method called when loading screen
     */
    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'new_employee.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'new_employee.fxml'.";
        assert etxtCourierLogin != null : "fx:id=\"etxtCourierLogin\" was not injected: check your FXML file 'new_employee.fxml'.";
        assert etxtCourierPassword != null : "fx:id=\"etxtCourierPassword\" was not injected: check your FXML file 'new_employee.fxml'.";
        assert etxtName != null : "fx:id=\"etxtName\" was not injected: check your FXML file 'new_employee.fxml'.";
        assert etxtSurname != null : "fx:id=\"etxtSurname\" was not injected: check your FXML file 'new_employee.fxml'.";
        assert etxtPhone != null : "fx:id=\"etxtPhone\" was not injected: check your FXML file 'new_employee.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'new_employee.fxml'.";

    }
}
