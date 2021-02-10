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

/**
 * class that handles machines statistics screen (machines.fxml)
 */
public class MachinesController {

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
    private Text etxtMessage;

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
     * table with statistics of machines
     */
    @FXML
    private TableView tbMachine;

    /**
     * column with machine ID
     */
    @FXML
    private TableColumn<Machine, Integer> tbRowID;

    /**
     * column with machine address
     */
    @FXML
    private TableColumn<Machine, String> tbRowAddress;

    /**
     * column with date
     */
    @FXML
    private TableColumn<Machine, String> tbRowDate;

    /**
     * column with the sum of sent parcels with the ID of this machine on a given day
     */
    @FXML
    private TableColumn<Machine, Integer> tbRowToPickup;

    /**
     * column with the sum of parcels picked up from this parcel locker on a given day
     */
    @FXML
    private TableColumn<Machine, Integer> tbRowPickedup;

    /**
     * column with the sum of parcels to pickup from this machine on a given day
     */
    @FXML
    private TableColumn<Machine, Integer> tbRowToPickupClient;

    /**
     * column with the sum of parcels picked up by courier (missed by addressee) from this machine on a given day
     */
    @FXML
    private TableColumn<Machine, Integer> tbRowMissed;

    /**
     * column with the sum of parcels picked up by addressee from this machine on a given day
     */
    @FXML
    private TableColumn<Machine, Integer> tbRowPickedupClient;

    /**
     * editable text field to search machine stats by ID
     */
    @FXML
    private TextField etxtSearchID;

    /**
     * editable text field to machine stats by date
     */
    @FXML
    private TextField etxtSearchDate;

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
            etxtSearchDate.setDisable(false);
            etxtSearchID.setDisable(false);
            MachineDAO machineDAO = new MachineDAO(dbUtil);

            try {
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

    /**
     * logout method
     *
     * @param event information about event
     */
    @FXML
    void logout(ActionEvent event) {
        btnLogin.setDisable(false);
        btnLogout.setDisable(true);
        etxtSearchDate.setDisable(true);
        etxtSearchID.setDisable(true);
        etxtSearchDate.setText("");
        etxtSearchID.setText("");
    }

    /**
     * method of seatching machine
     *
     * @param event information about event
     */
    @FXML
    void search(ActionEvent event) {
        try {
            MachineDAO machineDAO = new MachineDAO(dbUtil);
            if (etxtSearchDate.getText().isEmpty() && etxtSearchID.getText().isEmpty()) {
                tbMachine.setItems(machineDAO.showAllMachines());
            } else {
                tbMachine.setItems(machineDAO.searchMachine(etxtSearchDate.getText(), etxtSearchID.getText()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * method called when loading screen
     */
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
