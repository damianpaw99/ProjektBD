package edu.ib.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.machineStats.Machine;
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

public class MachinesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField etxtLogin;

    @FXML
    private PasswordField etxtPassword;

    @FXML
    private Text etxtMessage;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnLogout;

    @FXML
    private TableView tbMachine;

    @FXML
    private TableColumn<Machine, Integer> tbRowID;

    @FXML
    private TableColumn<Machine, String> tbRowAddress;

    @FXML
    private TableColumn<Machine, String> tbRowDate;

    @FXML
    private TableColumn<Machine, Integer> tbRowToPickup;

    @FXML
    private TableColumn<Machine, Integer> tbRowPickedup;

    @FXML
    private TableColumn<Machine, Integer> tbRowToPickupClient;

    @FXML
    private TableColumn<Machine, Integer> tbRowMissed;

    @FXML
    private TableColumn<Machine, Integer> tbRowPickedupClient;

    @FXML
    private TextField etxtSearchID;

    @FXML
    private TextField etxtSearchDate;

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
            etxtSearchDate.setDisable(false);
            etxtSearchID.setDisable(false);
            MachineDAO machineDAO=new MachineDAO(dbUtil);

            try{
                tbMachine.setItems(machineDAO.showAllMachines());
            } catch (SQLException throwables) {
                etxtMessage.setText("Błąd bazy danych");
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            etxtMessage.setText("Zalogowano pomyślnie");
        } catch (SQLException throwables) {
            etxtMessage.setText("Nieprawidłowy login lub hasło");
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(ActionEvent event) {
        btnLogin.setDisable(false);
        btnLogout.setDisable(true);
        etxtSearchDate.setDisable(true);
        etxtSearchID.setDisable(true);
        etxtSearchDate.setText("");
        etxtSearchID.setText("");
    }

    @FXML
    void search(ActionEvent event) {
        try {
            MachineDAO machineDAO = new MachineDAO(dbUtil);
            if (etxtSearchDate.getText().isEmpty() && etxtSearchID.getText().isEmpty()) {
                tbMachine.setItems(machineDAO.showAllMachines());
            } else {
                tbMachine.setItems(machineDAO.searchMachine(etxtSearchDate.getText(),etxtSearchID.getText()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtMessage != null : "fx:id=\"etxtMessage\" was not injected: check your FXML file 'machines.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'machines.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbMachine != null : "fx:id=\"tbMachine\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowID != null : "fx:id=\"tbRowID\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowAddress != null : "fx:id=\"tbRowAddress\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowDate != null : "fx:id=\"tbRowDate\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowToPickup != null : "fx:id=\"tbRowToPickup\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowPickedup != null : "fx:id=\"tbRowPickedup\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowToPickupClient != null : "fx:id=\"tbRowToPickupClient\" was not injected: check your FXML file 'machines.fxml'.";
        assert tbRowMissed != null : "fx:id=\"tbRowMissed\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtSearchID != null : "fx:id=\"etxtSearchID\" was not injected: check your FXML file 'machines.fxml'.";
        assert etxtSearchDate != null : "fx:id=\"etxtSearchDate\" was not injected: check your FXML file 'machines.fxml'.";

    }
}
