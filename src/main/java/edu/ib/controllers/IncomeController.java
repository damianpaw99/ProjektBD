package edu.ib.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.incomeView.Income;
import edu.ib.incomeView.IncomeDAO;
import edu.ib.machineStats.MachineDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private TableView tbIncome;

    @FXML
    private TableColumn<Income, String> tbRowDate;

    @FXML
    private TableColumn<Income, Double> tbRowIncome;

    @FXML
    private TextField etxtSearch;

    private DBUtil dbUtil;

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/administrator_menu.fxml"));
            logout(event);
            stage.setScene(new Scene(root, 1000, 800));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) {
        try {
            dbUtil = new DBUtil(etxtLogin.getText(), etxtPassword.getText());
            dbUtil.dbConnect();
            dbUtil.dbDisconnect();
            btnLogin.setDisable(true);
            btnLogout.setDisable(false);
            etxtSearch.setDisable(false);
            IncomeDAO incomeDAO=new IncomeDAO(dbUtil);

            try{
                tbIncome.setItems(incomeDAO.showAllIncome());
            } catch (SQLException throwables) {
                txtMessage.setText("Błąd bazy danych");
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            txtMessage.setText("Zalogowano pomyślnie");
        } catch (SQLException throwables) {
            txtMessage.setText("Nieprawidłowy login lub hasło");
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(ActionEvent event) {
        dbUtil=null;
        btnLogin.setDisable(false);
        btnLogout.setDisable(true);
        etxtSearch.setDisable(true);
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
