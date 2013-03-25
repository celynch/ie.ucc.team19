package ie.ucc.team19.dao;

/**
 *
 * @author
 */
//import java.text.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class DBConnectionManager {

    // TODO initialise from xml
    private static final String DBServerName = "localhost";
    private static final String DBName = "team19";
    private static final String username = "root";
    private static final String password = "eizeikem";
    private Statement statementObject;
    private Connection connectionObject;

    private void OpenDatabaseConnection() {
        String URL = "jdbc:mysql://" + DBServerName + "/" + DBName;

        try { // Initialiase drivers
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception exceptionObject) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            writeLogSQL(URL + " caused error " + exceptionObject.getMessage());
        }

        try { // Establish connection to database
            connectionObject = DriverManager.getConnection(URL, username, password);
        } catch (SQLException exceptionObject) {
            System.out.println("Problem with setting up " + URL);
            writeLogSQL(URL + " caused error " + exceptionObject.getMessage());
        }

    }

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

    public void Insert(String SQLinsert) {
        // Setup database connection details
        try {
            OpenDatabaseConnection();
            // Setup statement object
            statementObject = connectionObject.createStatement();

            // execute SQL commands to insert data
            statementObject.executeUpdate(SQLinsert);
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
   
    public ArrayList<Map<String, String[]>> Select(String SQLquery) {
        ArrayList<Map<String, String[]>> resultTable = new ArrayList<Map<String, String[]>>();

        try {// Make connection to database
            OpenDatabaseConnection();
            statementObject = connectionObject.createStatement();
            ResultSet statementResult = statementObject.executeQuery(SQLquery);

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
