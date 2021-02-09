package edu.ib.machineStats;

import edu.ib.DBUtil;
import edu.ib.incomeView.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to get data from database from machines_stats view
 */
public class MachineDAO {
    /**
     * Object used to connect to database
     */
    private DBUtil dbUtil;


    /**
     * Basic constructor
     *
     * @param dbUtil Object used to connect to database
     */
    public MachineDAO(DBUtil dbUtil){
        this.dbUtil=dbUtil;
    }

    /**
     * Method changing data in ResultSet object to ObservableList
     *
     * @param rs Data as ResultSet
     * @return ObservableList object with data
     * @throws SQLException Thrown, when there was a problem witch database
     */
    private ObservableList<Machine> getIncomeList(ResultSet rs) throws SQLException {
        ObservableList<Machine> incomeObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            Machine machineStats = new Machine();
            machineStats.setId(rs.getInt("ID"));
            machineStats.setAddress(rs.getString("Address"));
            machineStats.setDate(rs.getString("Date"));
            machineStats.setSend(rs.getInt("Nadana"));
            machineStats.setPickupCourier(rs.getInt("Podjeta"));
            machineStats.setReadyToPickup(rs.getInt("Doodbioru"));
            machineStats.setPickedup(rs.getInt("Odebrana"));
            machineStats.setMissed(rs.getInt("Nieodebrana"));
            incomeObservableList.add(machineStats);
        }
        return incomeObservableList;
    }

    /**
     * Method connecting to database using DBUtil object and getting all data
     *
     * @return All parcels from machine_stats from database
     * @throws SQLException                Thrown, when there was a problem with database
     */
    public ObservableList<Machine> showAllMachines() throws ClassNotFoundException, SQLException{
        String statement = "SELECT * FROM machine_stats";

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
     * @param date      Date to search in machine_stats
     * @param machine   Machine ID or address to search in machine_stats
     * @return Searched parcels machines from machine_stats view from database
     * @throws SQLException                Thrown, when there was a problem with database
     * @throws NumberFormatException       Thrown, when value is not an int, when searching parcels id
     */
    public ObservableList<Machine> searchMachine(String date, String machine) throws ClassNotFoundException, SQLException, NumberFormatException {
        String statement="";
        if(date.isEmpty()){
            statement="SELECT * FROM machine_stats WHERE " +
                    "ID LIKE \"%"+machine+"%\" " +
                    "OR Address LIKE \"%"+machine+"%\"";
        } else if(machine.isEmpty()){
            statement = "SELECT * FROM machine_stats WHERE " +
                    "Date LIKE \"%"+date+"%\"";
        } else{
            statement = "SELECT * FROM machine_stats WHERE " +
                    "Date LIKE \"%"+date+"%\" " +
                    "AND (ID LIKE \"%"+machine+"%\" " +
                    "OR Address LIKE \"%"+machine+"%\")";
        }

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(statement);
            return getIncomeList(resultSet);

        } catch (SQLException e) {
            throw e;
        }
    }
}
