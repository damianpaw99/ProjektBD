package edu.ib.controllers;

import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.Logger;
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

/**
 * class that handles new customer creation screen (new_customer.fxml)
 */
public class NewCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * editable text field to enter firstname of new customer
     */
    @FXML
    private TextField etxtName;

    /**
     * editable text field to enter surname of new customer
     */
    @FXML
    private TextField etxtSurname;

    /**
     * editable text field to enter email of new customer
     */
    @FXML
    private TextField etxtEmail;

    /**
     * editable text field to enter phone number of new customer
     */
    @FXML
    private TextField etxtPhone;

    /**
     * editable text field to enter new login
     */
    @FXML
    private TextField etxtLogin;

    /**
     * editable text field to enter new password
     */
    @FXML
    private PasswordField etxtPassword;

    /**
     * editable text field to repeat password
     */
    @FXML
    private PasswordField etxtRepeatPassword;

    /**
     * text field with information about success or failure of acion
     */
    @FXML
    private Text txtMessage;

    /**
     * button to add new customer
     */
    @FXML
    private Button btnAddClient;

    /**
     * button to return to home screen
     */
    @FXML
    private Button btnBack;

    /**
     * object used to connect to database
     */
    private DBUtil dbUtil;

    /**
     * method of adding new customer
     *
     * @param event information about event
     */
    @FXML
    void addClient(ActionEvent event) {

        try {
            if (!etxtPassword.getText().equals(etxtRepeatPassword.getText())) {
                txtMessage.setText("Hasła nie są identyczne!");
                throw new IllegalArgumentException("Passwords are not the same!");
            }
            if (etxtPassword.getText().length() > 10) {
                txtMessage.setText("Hasło jest za długie!");
                throw new IllegalArgumentException("Too long paswsowrd");
            }
            String name = etxtName.getText();
            String surname = etxtSurname.getText();
            String email = etxtEmail.getText();
            if (!email.contains("@")) {
                txtMessage.setText("Niepoprawny adres e-mail!");
                throw new IllegalArgumentException("Illegal email");
            }
            String phone = etxtPhone.getText();
            String login = etxtLogin.getText();
            if (name.isEmpty() || surname.isEmpty() || phone.isEmpty() || login.isEmpty()) {
                txtMessage.setText("Pola nie mogą być puste!");
                throw new IllegalArgumentException("Fields can't be empty");
            }

            StringBuilder statement = new StringBuilder();
            statement.append("CALL add_customer(\"");
            statement.append(login);
            statement.append("\",\"");
            statement.append(Logger.hash(etxtPassword.getText()));
            statement.append("\",\"");
            statement.append(name);
            statement.append("\",\"");
            statement.append(surname);
            statement.append("\",\"");
            statement.append(email);
            statement.append("\",");
            statement.append(phone);
            statement.append(")");

            dbUtil.dbExecuteUpdate(statement.toString());

            txtMessage.setText("Dodanie konta zakończyło się sukcesem!");
        } catch (SQLIntegrityConstraintViolationException e) {
            txtMessage.setText("Login +" + etxtLogin.getText() + " jest już zajęty!");
            e.getStackTrace();
        } catch (Exception e) {
            txtMessage.setText("Wystąpił problem! Spróbuj ponownie później!");
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
            e.printStackTrace();
        }
    }

    /**
     * method called when loading screen
     */
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
        dbUtil = new DBUtil("basic", "haslo100");
    }
}
