package edu.ib.parcelAdministrator;

import edu.ib.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to get data from database from parcels_history view
 */
public class ParcelAdministratorDAO{
    /**
     * Object used to connect to database
     */
    private DBUtil dbUtil;


    /**
     * Basic constructor
     *
     * @param dbUtil Object used to connect to database
     */
    public ParcelAdministratorDAO(DBUtil dbUtil) {
        this.dbUtil = dbUtil;

    }

    /**
     * Method changing data in ResultSet object to ObservableList
     *
     * @param rs Data as ResultSet
     * @return ObservableList object with data
     * @throws SQLException Thrown, when there was a problem witch database
     */
    private ObservableList<ParcelAdministrator> getParcelHistoryList(ResultSet rs) throws SQLException {
        ObservableList<ParcelAdministrator> parcelHistoryObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            ParcelAdministrator parcelAdministrator = new ParcelAdministrator();
            parcelAdministrator.setParcelId(rs.getInt("ParcelID"));
            parcelAdministrator.setDate(rs.getDate("Date").toString() + " " + rs.getTime("Date").toString());
            parcelAdministrator.setClientLogin(rs.getString("SenderLogin"));
            parcelAdministrator.setState(rs.getString("Status"));
            parcelHistoryObservableList.add(parcelAdministrator);
        }
        return parcelHistoryObservableList;
    }

    /**
     * Method connecting to database using DBUtil object and getting all data
     *
     * @return All parcels from parcels_history from database
     * @throws SQLException                Thrown, when there was a problem with database
     */
    public ObservableList<ParcelAdministrator> showAllParcels() throws ClassNotFoundException, SQLException{
        String statement = "SELECT * FROM parcels_history";

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
     * @param value      Data to search in parcels_history
     * @return Searched parcels machines from parcels_history view from database
     * @throws SQLException                Thrown, when there was a problem with database
     * @throws NumberFormatException       Thrown, when value is not an int, when searching parcels id
     */
    public ObservableList<ParcelAdministrator> searchParcel(String value) throws ClassNotFoundException, SQLException, NumberFormatException {
        String statement = "SELECT * FROM parcels_history WHERE "+
                "ParcelId LIKE \"%"+value+"%\""+
                "SenderLogin LIKE \"%"+value+"%\""+
                "Date LIKE \"%"+value+"%\""+
                "Status LIKE \"%"+value+"%\"";
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);
            return getParcelHistoryList(resultSet);

        } catch (SQLException e) {
            throw e;
        }
    }
}
