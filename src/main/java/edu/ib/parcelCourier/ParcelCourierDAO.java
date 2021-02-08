package edu.ib.parcelCourier;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to get data from database from courier_parcels view
 */
public class ParcelCourierDAO {
    /**
     * Object used to connect to database
     */
    private DBUtil dbUtil;
    /**
     * Object with user logging in, used to verify if user is logged in
     */
    private final Logger logger;

    /**
     * Basic constructor
     *
     * @param dbUtil Object used to connect to database
     * @param logger Object used to confirm that person logged in
     */
    public ParcelCourierDAO(DBUtil dbUtil, Logger logger) {
        this.dbUtil = dbUtil;
        this.logger = logger;
    }

    /**
     * Method changing data in ResultSet object to ObservableList
     *
     * @param rs Data as ResultSet
     * @return ObservableList object with data
     * @throws SQLException Thrown, when there was a problem witch database
     */
    private ObservableList<ParcelCourier> getParcelCourierList(ResultSet rs) throws SQLException {
        ObservableList<ParcelCourier> parcelCourierObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            ParcelCourier parcelCourier = new ParcelCourier();
            parcelCourier.setParcelId(rs.getInt("ParcelID"));
            String outboxAddress = rs.getString("MachineOutboxAddress");
            String inboxAddress = rs.getString("MachineInboxAddress");
            String state = rs.getString("Status");
            String date = rs.getDate("Date").toString() + " " + rs.getTime("Date").toString();
            parcelCourier.setDate(date);
            parcelCourier.setState(state);
            parcelCourier.setInboxAddress(inboxAddress);
            parcelCourier.setOutboxAddress(outboxAddress);
            parcelCourierObservableList.add(parcelCourier);
        }
        return parcelCourierObservableList;
    }

    /**
     * Method connecting to database using DBUtil object and getting all data
     *
     * @return All parcels from courier_parcels from database for logged in user (in Logger class object)
     * @throws SQLException                Thrown, when there was a problem with database
     * @throws WrongLoginPasswordException Thrown, when person is not logged in (in Logger class object)
     */
    public ObservableList<ParcelCourier> showAllParcels() throws ClassNotFoundException, SQLException, WrongLoginPasswordException {
        String statement = "SELECT ParcelID, Status, MachineOutboxAddress, MachineInboxAddress, Date FROM courier_parcels WHERE CourierLogin=\"" + logger.getLogin() + "\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelCourierList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Method connecting to database using DBUtil object and getting searched data
     *
     * @param value Data to search in machine_senders_view
     * @return Searched parcels machines from parcels_history view from database for a logged user (in Logger object)
     * @throws SQLException                Thrown, when there was a problem with database
     * @throws NumberFormatException       Thrown, when value is not an int, when searching parcels id
     * @throws WrongLoginPasswordException Thrown, when user is not logged in
     */
    public ObservableList<ParcelCourier> searchParcel(String value) throws ClassNotFoundException, SQLException, NumberFormatException, WrongLoginPasswordException {
        String statement = "SELECT ParcelID, Status, MachineOutboxAddress, MachineInboxAddress, Date FROM courier_parcels WHERE CourierLogin=\"" + logger.getLogin() + "\"";
        statement += " AND (ParcelID LIKE \"%" + value + "%\" OR Status LIKE \"%" + value + "%\" OR MachineOutboxAddress LIKE \"%" + value + "%\" OR MachineInboxAddress LIKE \"%" + value + "%\" OR Date LIKE \"%" + value + "%\")";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelCourierList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }
}
