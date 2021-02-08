package edu.ib.parcelLocker;

import edu.ib.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to get data from database from machines_senders_view view
 */
public class ParcelLockerDAO {
    /**
     * Object used to connect to database
     */
    private DBUtil dbUtil;

    /**
     * Basic constructor
     *
     * @param dbUtil Object used to connect to database
     */
    public ParcelLockerDAO(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    /**
     * Method changing data in ResultSet object to ObservableList
     *
     * @param rs Data as ResultSet
     * @return ObservableList object with data
     * @throws SQLException Thrown, when there was a problem witch database
     */
    private ObservableList<ParcelLocker> getParcelLockerList(ResultSet rs) throws SQLException {
        ObservableList<ParcelLocker> parcelLockerObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            ParcelLocker parcelLocker = new ParcelLocker();
            parcelLocker.setAddress(rs.getString("Adres"));
            parcelLocker.setId(rs.getInt("IDpaczkomatu"));
            parcelLockerObservableList.add(parcelLocker);
        }
        return parcelLockerObservableList;
    }

    /**
     * Method connecting to database using DBUtil object and getting all data
     *
     * @return All parcels machines from machine_senders_view from database
     * @throws SQLException Thrown, when there was a problem with database
     */
    public ObservableList<ParcelLocker> showAllParcelLocker() throws ClassNotFoundException, SQLException {
        String statement = "SELECT IDpaczkomatu, Adres FROM machines_senders_view";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelLockerList(resultSet);

        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Method connecting to database using DBUtil object and getting searched data
     *
     * @param data Data to search in machine_senders_view
     * @return Searched parcels machines from machine_senders_view from database
     * @throws SQLException Thrown, when there was a problem with database
     */
    public ObservableList<ParcelLocker> searchParcelLocker(String data) throws ClassNotFoundException, SQLException {
        String statement = "SELECT IDpaczkomatu, Adres FROM machines_senders_view WHERE Adres LIKE \"%" + data + "%\"";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getParcelLockerList(resultSet);

        } catch (SQLException e) {
            throw e;
        }
    }


}
