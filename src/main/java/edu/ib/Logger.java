package edu.ib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to log as a customer or client to database
 */
public class Logger {
    /**
     * Type of person to be logged (0 - CUSTOMER, 1 - EMPLOYEE)
     */
    private int typeLogged;
    /**
     * Object used to connect to database
     */
    private DBUtil dbUtil;
    /**
     * int representing EMPLOYEE
     */
    public static final int EMPLOYEE = 1;
    /**
     * int representing CUSTOMER
     */
    public static final int CUSTOMER = 0;
    /**
     * Login of person to log in to database
     */
    private final String login;
    /**
     * Boolean, which contains information about logging in (if it is successful or not)
     */
    private boolean loggedIn = false;

    /**
     * Base constructor
     *
     * @param dbUtil     DBUtil class object, used to connect to database
     * @param login      Login of person
     * @param typeLogged Type of person to log in
     */
    public Logger(DBUtil dbUtil, String login, int typeLogged) {
        if (typeLogged == EMPLOYEE || typeLogged == CUSTOMER) {
            this.typeLogged = typeLogged;
            this.login = login;
        } else {
            throw new IllegalArgumentException("Invalid logging person type number");
        }
        this.dbUtil = dbUtil;
    }

    /**
     * Method logging person
     *
     * @param hashedPassword Password hashed with Logger.hash()
     * @throws WrongLoginPasswordException Exception thrown, when login or password are incorect
     * @throws SQLException                Exception thrown, when there was a problem with database
     */
    public void logIn(String hashedPassword) throws WrongLoginPasswordException, SQLException, ClassNotFoundException {
        ResultSet result;
        String statement = "SELECT check_password(\"" + login + "\",\"" + hashedPassword + "\"," + typeLogged + ")";
        result = dbUtil.dbExecuteQuery(statement);
        result.next();
        if (result.getInt(1) == 0) {
            throw new WrongLoginPasswordException("Wrong login or password");
        } else {
            loggedIn = true;
        }
    }

    /**
     * Method used to hash password using SHA-1
     *
     * @param pass Password to hash
     * @return Hashed password
     */
    public static String hash(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(pass.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16)).substring(1);
        }
        return sb.toString();
    }

    /**
     * Login getter return only when person successfully logged in
     *
     * @return Login
     * @throws WrongLoginPasswordException Thrown when person is not logged in
     */
    public String getLogin() throws WrongLoginPasswordException {
        if (loggedIn) {
            return login;
        } else throw new WrongLoginPasswordException("Person is not logged in!");
    }
}
