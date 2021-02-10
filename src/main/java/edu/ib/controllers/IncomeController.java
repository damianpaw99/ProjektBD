package edu.ib.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.incomeView.Income;
import edu.ib.incomeView.IncomeDAO;
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

/**
 * class that handles income informations screen (income.fxml)
 */
public class IncomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * editable text field to enter login
     */
    @FXML
    private TextField etxtLogin;

    /**
     * editable text field to enter password
     */
    @FXML
    private PasswordField etxtPassword;

    /**
     * text field with information about success or failure of acion
     */
    @FXML
    private Text txtMessage;

    /**
     * login button
     */
    @FXML
    private Button btnLogin;

    /**
     * logout button
     */
    @FXML
    private Button btnLogout;

    /**
     * table with history of income
     */
    @FXML
    private TableView tbIncome;

    /**
     * column with date of income
     */
    @FXML
    private TableColumn<Income, String> tbRowDate;

    /**
     * column with income during the day
     */
    @FXML
    private TableColumn<Income, Double> tbRowIncome;

    /**
     * editable text field to search date
     */
    @FXML
    private TextField etxtSearch;

    /**
     * object used to connect to database
     */
    private DBUtil dbUtil;

    /**
     * method of returning to previous screen
     *
     * @param event information about event
     */
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

    /**
     * login method
     *
     * @param event information about event
     */
    @FXML
    void login(ActionEvent event) {
        try {
            dbUtil = new DBUtil(etxtLogin.getText(), etxtPassword.getText());
            dbUtil.dbConnect();
            dbUtil.dbDisconnect();
            btnLogin.setDisable(true);
            btnLogout.setDisable(false);
            etxtSearch.setDisable(false);
            IncomeDAO incomeDAO = new IncomeDAO(dbUtil);

            try {
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

    /**
     * logout method
     *
     * @param event information about event
     */
    @FXML
    void logout(ActionEvent event) {
        dbUtil = null;
        btnLogin.setDisable(false);
        btnLogout.setDisable(true);
        etxtSearch.setDisable(true);
    }

    /**
     * method of seatching income for the day
     *
     * @param event information about event
     */
    @FXML
    void search(ActionEvent event) {

    }

    /**
     * method called when loading screen
     */
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
