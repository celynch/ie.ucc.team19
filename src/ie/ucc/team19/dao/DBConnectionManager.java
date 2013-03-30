package ie.ucc.team19.dao;

import ie.ucc.team19.service.PropertiesReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

/**
 * DAO to handle connections via JDBC, gets credentials from properties.
 * Exceptions write to log via writeLogSQL method. 
 * @author Cormac
 */
public class DBConnectionManager {

    private static String DBServerName;// = "localhost";
    private static String DBName;// = "team19";
    private static String DBusername;// = "root";
    private static String DBpassword;// = "eizeikem";
    private PreparedStatement statementObject;
    private Connection connectionObject;

    /**
     * Constructor for DBConnectionManager. Credentials read from
     * ServletContext scoped properties reader, reads from setup.txt.
     * @param properties Properties reader for credentials.
     */
    public DBConnectionManager(PropertiesReader properties) {
        DBServerName = properties.getDBServerName();
        DBName = properties.getDBName();
        DBusername = properties.getDBusername();
        DBpassword = properties.getDBpassword();
    }

    /**
     * Instantiates JDBC driver object and opens connection to db.
     */
    private void OpenDatabaseConnection() {
        String URL = "jdbc:mysql://" + DBServerName + "/" + DBName;

        try { // Initialiase drivers
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception exceptionObject) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            writeLogSQL(URL + " caused error " + exceptionObject.getMessage());
        }

        try { // Establish connection to database
            connectionObject = DriverManager.getConnection(URL, DBusername, DBpassword);
        } catch (SQLException exceptionObject) {
            System.out.println("Problem with setting up " + URL);
            writeLogSQL(URL + " caused error " + exceptionObject.getMessage());
        }

    }

    /**
     * Closes connection to db. Releases resources.
     */
    private void CloseDatabaseConnection() {
        try {
            // Establish connection to database
            connectionObject.close();
        } catch (SQLException exceptionObject) {
            System.out.println("Problem with closing up ");
            writeLogSQL("closing caused error " + exceptionObject.getMessage());
        } catch (NullPointerException exNul) {
            System.out.println("null was here");
        }
    } //CloseDatabaseConnection

    /**
     * Prepares statement with mapped SQL types, uses db connection to
     *  execute statement. Opens and closes connection per query.
     * @param SQLinsert - the raw query string to execute.
     * @param params - parameters to map to SQL types and add to query.
     */
    public void Insert(String SQLinsert, Object[] params) {
        // Setup database connection details
        try {
            OpenDatabaseConnection();
            // Setup statement object
            statementObject = connectionObject.prepareStatement(SQLinsert);
            for(int i = 0; i < params.length; i ++) {
                int sqlType = SqlMapper.getSqlTypeFromClass(params[i]);
                if(sqlType == -1) {
                    statementObject.setString(i+1, params[i].toString());
                } else {
                    statementObject.setObject(i+1, params[i], sqlType);
                }
            }
            // execute SQL commands to insert data
            statementObject.executeUpdate();
            writeLogSQL(SQLinsert +" Executed OK");
        } catch (SQLException exceptionObject) {
            System.out.println(SQLinsert+" - Problem is : " + exceptionObject.getMessage());
            writeLogSQL(SQLinsert + " caused error " + exceptionObject.getMessage());
        } finally {
            try {
                statementObject.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement " + e.getMessage() + " " + e.getErrorCode());
                writeLogSQL(SQLinsert + " caused error " + e.getClass().toString());
                e.printStackTrace();
            }
            CloseDatabaseConnection();
        }
    }

    /**
     * For Nullipotent queries. Prepares statement with mapped SQL types,
     * uses db connection to execute statement, resultset entered to map.
     * Opens and closes connection per query.
     * @param SQLinsert - the raw query string to execute.
     * @param params - parameters to map to SQL types and add to query.
     * @return rows of result as list of maps
     */
    public ArrayList<Map<String, String[]>> Select(String SQLquery, Object[] params) {
        ArrayList<Map<String, String[]>> resultTable = new ArrayList<Map<String, String[]>>();

        try {// Make connection to database
            OpenDatabaseConnection();
            statementObject = connectionObject.prepareStatement(SQLquery);
            for(int i = 0; i < params.length; i ++) {
                int sqlType = SqlMapper.getSqlTypeFromClass(params[i]);
                if(sqlType == -1) {
                    statementObject.setString(i+1, params[i].toString());
                } else {
                    statementObject.setObject(i+1, params[i], sqlType);
                }
            }
            ResultSet statementResult = statementObject.executeQuery();

            ResultSetMetaData rsMetaData = statementResult.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();
            String[] columnLabels = new String[numberOfColumns];
            for(int i = 1; i <= numberOfColumns; i++) {
                columnLabels[i-1] = rsMetaData.getColumnLabel(i);
            }

            statementResult.beforeFirst();
            while (statementResult.next()) { // While there are rows to process
                Map<String, String[]> rowResult = new HashMap<String, String[]>();
                for(String columnLabel : columnLabels) {
                    String sqlValue = statementResult.getString(columnLabel);
                    String[] values = {sqlValue};
                    rowResult.put(columnLabel, values);
                }
                resultTable.add(rowResult);
            }
        } catch (SQLException e) {
            System.err.println("Select problems with SQL " + SQLquery);
            System.err.println("Select problem is " + e.getMessage() + " " + e.getErrorCode());
            writeLogSQL(SQLquery + " caused error " + e.getClass().toString());
        } finally {
            try {
                statementObject.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement " + e.getMessage() + " " + e.getErrorCode());
                writeLogSQL(SQLquery + " caused error " + e.getClass().toString());
                e.printStackTrace();
            }
            CloseDatabaseConnection();
        }

        return resultTable;
    } // End Select

    /**
     * Called on exception, writes exception message to file.
     * @param message
     */
    private void writeLogSQL(String message) {
        PrintStream output;
        try {
            output = new PrintStream(new FileOutputStream("sql-logfile.txt", true));
            output.println(new java.util.Date() + " " + message);
            output.close();
        } catch (IOException ieo) {
            System.out.println("MAJOR writeLogSQL- Problem is : " + ieo.getMessage());
        }
    } // End writeLog
} //End dblib
