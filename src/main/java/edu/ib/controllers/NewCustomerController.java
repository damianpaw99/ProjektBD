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

    private DBUtil dbUtil;

    @FXML
    void addClient(ActionEvent event) {

        try {
            if(!etxtPassword.getText().equals(etxtRepeatPassword.getText())){
                txtMessage.setText("Hasła nie są identyczne!");
                throw new IllegalArgumentException("Passwords are not the same!");
            }
            if (etxtPassword.getText().length()>10){
                txtMessage.setText("Hasło jest za długie!");
                throw new IllegalArgumentException("Too long paswsowrd");
            }
            String name=etxtName.getText();
            String surname=etxtSurname.getText();
            String email=etxtEmail.getText();
            if(!email.contains("@")){
                txtMessage.setText("Niepoprawny adres e-mail!");
                throw new IllegalArgumentException("Illegal email");
            }
            String phone=etxtPhone.getText();
            String login=etxtLogin.getText();
            if (name.isEmpty() || surname.isEmpty() || phone.isEmpty() || login.isEmpty()){
                txtMessage.setText("Pola nie mogą być puste!");
                throw new IllegalArgumentException("Fields can't be empty");
            }

            StringBuilder statement=new StringBuilder();
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
        } catch(SQLIntegrityConstraintViolationException e){
            txtMessage.setText("Login +"+etxtLogin.getText()+" jest już zajęty!");
            e.getStackTrace();
        } catch (Exception e) {
            txtMessage.setText("Wystąpił problem! Spróbuj ponownie później!");
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
        dbUtil=new DBUtil("basic","haslo100");
    }
}
