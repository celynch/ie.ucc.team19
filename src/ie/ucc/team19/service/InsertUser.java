package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.UUID;

public class InsertUser {
    public static void insertStudent(StudentBean student) {
        String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").format((java.sql.Date)student.getDate_of_birth());
        
        String query;
        query = "INSERT INTO Students VALUES" +
                "(NULL, "
                + "'" + student.getFirst_name() +"', "
                + "'" + student.getLast_name() + "', "
                + "'" + student.getEmail() + "', "
                + "'" + student.getPassword_hash() + "', "
                + "'" + student.getAddress_line1() + "', "
                + "'" + student.getAddress_line2() + "', "
                + "'" + student.getAddress_line3() + "', "
                + "'" + student.getCountry() + "', "
                + "'" + student.getTelephone() + "', "
                + "'" + student.getDate_of_birth() + "', "
                + "'" + student.getGender() + "', "
                + "false" + ", "
                + "'" + UUID.randomUUID().toString() + "', "
                + "'" + dateNow + "')";

        DBConnectionManager connector = new DBConnectionManager();
        connector.OpenDatabaseConnection("localhost", "team19", "root","eizeikem");
        connector.Insert(query);
        connector.CloseDatabaseConnection();
    }
}
