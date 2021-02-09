package edu.ib.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.ib.DBUtil;
import edu.ib.parcelAdministrator.ParcelAdministrator;
import edu.ib.parcelAdministrator.ParcelAdministratorDAO;
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

public class ParcelsController {

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
    private Button btnlogout;

    @FXML
    private TableView tbParcels;

    @FXML
    private TableColumn<ParcelAdministrator, String> tbRowSender;

    @FXML
    private TableColumn<ParcelAdministrator, Integer> tbRowParcelId;

    @FXML
    private TableColumn<ParcelAdministrator, String> tbRowDate;

    @FXML
    private TableColumn<ParcelAdministrator, String> tbRowState;

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
            e.printStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) {
        try {
            dbUtil = new DBUtil(etxtLogin.getText(), etxtPassword.getText());
            dbUtil.dbConnect();
            dbUtil.dbDisconnect();
            btnLogin.setDisable(true);
            btnlogout.setDisable(false);
            etxtSearch.setDisable(false);
            ParcelAdministratorDAO parcelAdministratorDAO=new ParcelAdministratorDAO(dbUtil);
            try{
                tbParcels.setItems(parcelAdministratorDAO.showAllParcels());
            } catch (SQLException throwables) {
                txtMessage.setText("Błąd bazy danych");
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            txtMessage.setText("Nieprawidłowy login lub hasło");
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void logout(ActionEvent event) {
        tbParcels.getItems().clear();
        dbUtil=null;
        btnLogin.setDisable(false);
        btnlogout.setDisable(true);
        etxtSearch.setText("");
        etxtSearch.setDisable(true);
    }

    @FXML
    void search(ActionEvent event) {
        try {
            ParcelAdministratorDAO parcelAdministratorDAO = new ParcelAdministratorDAO(dbUtil);
            if (etxtSearch.getText().isEmpty()) {
                tbParcels.setItems(parcelAdministratorDAO.showAllParcels());
            } else {
                tbParcels.setItems(parcelAdministratorDAO.searchParcel(etxtSearch.getText()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert etxtLogin != null : "fx:id=\"etxtLogin\" was not injected: check your FXML file 'parcels.fxml'.";
        assert etxtPassword != null : "fx:id=\"etxtPassword\" was not injected: check your FXML file 'parcels.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'parcels.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'parcels.fxml'.";
        assert btnlogout != null : "fx:id=\"btnlogout\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbParcels != null : "fx:id=\"tbParcels\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowSender != null : "fx:id=\"tbRowSender\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowParcelId != null : "fx:id=\"tbRowParcelId\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowDate != null : "fx:id=\"tbRowDate\" was not injected: check your FXML file 'parcels.fxml'.";
        assert tbRowState != null : "fx:id=\"tbRowState\" was not injected: check your FXML file 'parcels.fxml'.";
        assert etxtSearch != null : "fx:id=\"etxtSearch\" was not injected: check your FXML file 'parcels.fxml'.";

    }
}
