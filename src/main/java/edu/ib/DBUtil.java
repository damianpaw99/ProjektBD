package edu.ib;

import com.github.vldrus.sql.rowset.CachedRowSetWrapper;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;

/**
 * Class used to connect to database
 */
public class DBUtil {
    /**
     * Username
     */
    private String userName;
    /**
     * User password
     */
    private String userPassword;
    /**
     * Connection class object
     */
    private Connection conn = null;

    /**
     * Base constructor
     *
     * @param userName     Username
     * @param userPassword User password
     */
    public DBUtil(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * Method used to connect to database
     *
     * @throws SQLException Thrown, when there was a problem with database or login or password was incorrect
     */
    public void dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(createURL());
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * Method used to disconnect
     *
     * @throws SQLException Thrown, when there was a problem database
     */
    public void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Method creating URL used to connect to database
     *
     * @return URL
     */
    private String createURL() {

        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("parcel_database?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(userName);
        urlSB.append("&password=");
        urlSB.append(userPassword);
        urlSB.append("&serverTimezone=CET");

        return urlSB.toString();
    }

    /**
     * Method used to get data from database
     *
     * @param queryStmt SQL statement
     * @return Data
     * @throws SQLException Thrown, when was a problem with database or username/password was incorrect
     */
    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {

            dbConnect();

            stmt = conn.prepareStatement(queryStmt);

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetWrapper();

            crs.populate(resultSet);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }

        return crs;
    }

    /**
     * Method used to execute un update on database
     *
     * @param sqlStmt SQL statement
     * @throws SQLException Thrown, when there was a problem with database or username/password was incorrect
     */
    public void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

}
