/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ie.ucc.team19.dao;

/**
 *
 * @author Shiny
 */
//import java.text.*;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class DBConnectionManager {

    private Statement statementObject;
    private Connection connectionObject;
    // Managing java 'constants'
    //http://docs.oracle.com/javase/tutorial/essential/environment/properties.html

    //PROS AND CONS FOR OPENING CONNECTIONS ON AN AS NEEDEED BASIS?
    public void OpenDatabaseConnection(String DBServerNamein,String DSNin,String usernamein,String passwordin) {
        String DBName = DSNin;
        String DBServerName = DBServerNamein;
        String URL = "jdbc:mysql://"+DBServerName+"/" + DBName;
        String username = usernamein;
        String password = passwordin;

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

    } // DatabaseConnectorNew constructor

    public void CloseDatabaseConnection() {
        try {
            // Establish connection to database
            connectionObject.close(); //19/9/11
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
            // Setup statement object
            statementObject = connectionObject.createStatement();

            // execute SQL commands to insert data
            statementObject.executeUpdate(SQLinsert);
            writeLogSQL(SQLinsert +" Executed OK");
        } catch (SQLException exceptionObject) {
            System.out.println(SQLinsert+" - Problem is : " + exceptionObject.getMessage());
            writeLogSQL(SQLinsert + " caused error " + exceptionObject.getMessage());
            }
        } // End Insert
   
    public ArrayList<String[]> Select(String SQLquery) {
        ArrayList<String[]> resultTable = new ArrayList<String[]>();

        try {// Make connection to database
            statementObject = connectionObject.createStatement();
            ResultSet statementResult = statementObject.executeQuery(SQLquery);

            ResultSetMetaData rsMetaData = statementResult.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();
            statementResult.beforeFirst();

            while (statementResult.next()) { // While there are rows to process
                String[] rowResult = new String[numberOfColumns];
                for(int i = 0; i < numberOfColumns; i++) {
                    rowResult[i] = statementResult.getString(i+1);
                }
                resultTable.add(rowResult);
            }

        } catch (Exception e) {
            System.err.println("Select problems with SQL " + SQLquery);
            System.err.println("Select problem is " + e.getMessage());
            writeLogSQL(SQLquery + " caused error " + e.getMessage());
        }

        return resultTable;
    } // End Select

    private void writeLogSQL(String message) {
        PrintStream output;
        try {
            output = new PrintStream(new FileOutputStream("sql-logfile.txt", true));
            System.out.println(message);
            output.println(new java.util.Date() + " " + message);
            output.close();
        } catch (IOException ieo) {
            System.out.println("MAJOR writeLogSQL- Problem is : " + ieo.getMessage());
        }
    } // End writeLog
} //End dblib
