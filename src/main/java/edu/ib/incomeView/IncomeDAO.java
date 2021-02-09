package edu.ib.incomeView;

import edu.ib.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to get data from database from daily_income view
 */
public class IncomeDAO {
    /**
     * Object used to connect to database
     */
    private DBUtil dbUtil;


    /**
     * Basic constructor
     *
     * @param dbUtil Object used to connect to database
     */
    public IncomeDAO(DBUtil dbUtil){
        this.dbUtil=dbUtil;
    }

    /**
     * Method changing data in ResultSet object to ObservableList
     *
     * @param rs Data as ResultSet
     * @return ObservableList object with data
     * @throws SQLException Thrown, when there was a problem witch database
     */
    private ObservableList<Income> getIncomeList(ResultSet rs) throws SQLException {
        ObservableList<Income> incomeObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            Income parcelAdministrator = new Income();
            parcelAdministrator.setIncome(rs.getDouble("Income"));
            parcelAdministrator.setDate(rs.getString("Date"));
            incomeObservableList.add(parcelAdministrator);
        }
        return incomeObservableList;
    }

    /**
     * Method connecting to database using DBUtil object and getting all data
     *
     * @return All parcels from daily_income from database
     * @throws SQLException                Thrown, when there was a problem with database
     */
    public ObservableList<Income> showAllIncome() throws ClassNotFoundException, SQLException{
        String statement = "SELECT * FROM daily_income";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);

            return getIncomeList(resultSet);


        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Method connecting to database using DBUtil object and getting searched data
     *
     * @param value      Data to search in daily_income
     * @return Searched parcels machines from daily_income view from database
     * @throws SQLException                Thrown, when there was a problem with database
     * @throws NumberFormatException       Thrown, when value is not an int, when searching parcels id
     */
    public ObservableList<Income> searchIncome(String value) throws ClassNotFoundException, SQLException, NumberFormatException {
        String statement = "SELECT * FROM daily_income WHERE Date LIKE \"%"+value+"%\"";
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);
            return getIncomeList(resultSet);

        } catch (SQLException e) {
            throw e;
        }
    }

}
