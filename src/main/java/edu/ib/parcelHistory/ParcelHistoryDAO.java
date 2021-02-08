package edu.ib.parcelHistory;

import edu.ib.DBUtil;
import edu.ib.Logger;
import edu.ib.WrongLoginPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to get data from database from parcels_history view
 */
public class ParcelHistoryDAO {
    /**
     * Object used to connect to database
     */
    private DBUtil dbUtil;
    /**
     * Object with user logging in, used to verify if user is logged in
     */
    private final Logger logger;
    /**
     * Constant representing parcel id when searching (0)
     */
    public static final int PARCEL_ID = 0;
    /**
     * Constant representing parcal status when searching (1)
     */
    public static final int STATUS = 1;
    /**
     * Constant representing parcel date when searching (2)
     */
    public static final int DATE = 2;

    /**
     * Basic constructor
     *
     * @param dbUtil Object used to connect to database
     * @param logger Object used to confirm that person logged in
     */
    public ParcelHistoryDAO(DBUtil dbUtil, Logger logger) {
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
    private ObservableList<ParcelHistory> getParcelHistoryList(ResultSet rs) throws SQLException {
        ObservableList<ParcelHistory> parcelHistoryObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            ParcelHistory parcelHistory = new ParcelHistory();
            parcelHistory.setParcelId(rs.getInt("ParcelID"));
            parcelHistory.setDate(rs.getDate("Date").toString() + " " + rs.getTime("Date").toString());
            String state = rs.getString("Status");
            parcelHistory.setState(state);
            parcelHistoryObservableList.add(parcelHistory);
        }
        return parcelHistoryObservableList;
    }

    /**
     * Method connecting to database using DBUtil object and getting all data
     *
     * @return All parcels from machine_senders_view from database for logged in user (in Logger class object)
     * @throws SQLException                Thrown, when there was a problem with database
     * @throws WrongLoginPasswordException Thrown, when person is not logged in (in Logger class object)
     */
    public ObservableList<ParcelHistory> showAllParcels() throws ClassNotFoundException, SQLException, WrongLoginPasswordException {
        String statement = "SELECT ParcelID, Date, Status FROM parcels_history WHERE SenderLogin=\"" + logger.getLogin() + "\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelHistoryList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Method connecting to database using DBUtil object and getting searched data
     *
     * @param value      Data to search in parcels_history view
     * @param itemSearch Number representing what data to search in database
     * @return Searched parcels machines from parcels_history view from database for a logged user (in Logger object)
     * @throws SQLException                Thrown, when there was a problem with database
     * @throws NumberFormatException       Thrown, when value is not an int, when searching parcels id
     * @throws WrongLoginPasswordException Thrown, when user is not logged in
     */
    public ObservableList<ParcelHistory> searchParcel(String value, int itemSearch) throws ClassNotFoundException, SQLException, NumberFormatException, WrongLoginPasswordException {
        String type;
        switch (itemSearch) {
            case PARCEL_ID -> {
                type = "ParcelID";
                Integer.parseInt(value);
            }
            case STATUS -> {
                type = "Status";
                value = "\"%" + value + "%\"";
            }
            case DATE -> {
                type = "Date";
                value = "\"%" + value + "%\"";
            }
            default -> throw new IllegalArgumentException();
        }

        String statement = "SELECT ParcelID, Date, Status FROM parcels_history WHERE SenderLogin=\"" + logger.getLogin() + "\" AND " + type + " LIKE " + value;
        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelHistoryList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }
}
