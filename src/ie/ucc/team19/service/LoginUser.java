package ie.ucc.team19.service;

import java.util.ArrayList;
import java.util.Map;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

public class LoginUser {
    public static StudentBean loginStudent(String email, String password_hash) {
        StudentBean student = new StudentBean();
        String query = "SELECT * FROM Students WHERE email = '" + email + "'";
        DBConnectionManager connector = new DBConnectionManager();
        connector.OpenDatabaseConnection("localhost", "team19", "root","eizeikem");
        ArrayList<Map<String, String[]>> studentDetails = connector.Select(query);
        if(studentDetails.size() != 0) {    // email found
            if( studentDetails.get(0).get(password_hash)[0].equals(password_hash)) { // password matches
                
            }
        }
        connector.CloseDatabaseConnection();
        return student;
    }
}
