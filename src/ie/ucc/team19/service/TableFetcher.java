package ie.ucc.team19.service;
import ie.ucc.team19.dao.DBConnectionManager;

import java.util.ArrayList;


public class TableFetcher {
    public ArrayList<String[]> getCourseDetails(String course_id) {
        course_id = course_id.replace("'", "''");
        String query = "SELECT * FROM Courses WHERE course_id = \'"
                + course_id + "\'";
        ArrayList<String[]> resultTable = fetchTable(query);
        return resultTable;
    }
    
    public ArrayList<String[]> getCourseLecturers(String course_id) {
        course_id = course_id.replace("'", "''");
        String query = "SELECT * FROM Lecturers WHERE lecturer_id IN (SELECT lecturer_id FROM Teaches WHERE course_id = \'"
                + course_id + "\')";
        ArrayList<String[]> resultTable = fetchTable(query);
        return resultTable;
    }

    public ArrayList<String[]> getCourseVenues(String course_id) {
        course_id = course_id.replace("'", "''");
        String query = "SELECT * FROM Venues WHERE venue_id IN (SELECT venue_id FROM course_locations WHERE course_id = \'"
                + course_id + "\')";
        ArrayList<String[]> resultTable = fetchTable(query);
        return resultTable;
    }
    
    private ArrayList<String[]> fetchTable(String query) {
        DBConnectionManager connector = new DBConnectionManager();
        connector.OpenDatabaseConnection("localhost", "team19", "root","eizeikem");
        ArrayList<String[]> resultTable = connector.Select(query);
        connector.CloseDatabaseConnection();
        return resultTable;
    }
}
