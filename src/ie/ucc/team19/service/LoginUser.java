package ie.ucc.team19.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
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
            if( studentDetails.get(0).get("password_hash")[0].equals(password_hash)) { // password matches
                try {
                    BeanUtilsBean.getInstance().populate(student, studentDetails.get(0));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Error populating student bean");
                    e.printStackTrace();
                }
            }
        }
        connector.CloseDatabaseConnection();
        return student;
    }

    public static StudentBean cookieValidate(String email, String cookie_token) {
        StudentBean student = new StudentBean();
        String query = "SELECT * FROM Students WHERE email = '" + email + "'";
        DBConnectionManager connector = new DBConnectionManager();
        connector.OpenDatabaseConnection("localhost", "team19", "root","eizeikem");
        ArrayList<Map<String, String[]>> studentDetails = connector.Select(query);
        if(studentDetails.size() != 0) {    // email found
            if( studentDetails.get(0).get("cookie_token")[0].equals(cookie_token)) { // password matches
                try {
                    BeanUtilsBean.getInstance().populate(student, studentDetails.get(0));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Error populating student bean");
                    e.printStackTrace();
                }
            }
        }
        connector.CloseDatabaseConnection();
        return student;
    }
}
