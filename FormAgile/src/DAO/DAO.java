package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NgocPJa
 */
public class DAO {

    private Connection conn = null;
    final String hostName = "localhost";
    final String dbName = "QUANLYKHO_AGILE";
    final String dbUserName = "Duy";
    final String dbPassword = "1";
    final String connectionURL = "jdbc:sqlserver://" + hostName
            + ":1433;databaseName=" + dbName;

    public DAO() {
    }

    public Connection openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionURL, dbUserName, dbPassword);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public boolean closeConnection() {
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) {
        DAO dao = new  DAO();
        dao.openConnection();
    }

}
