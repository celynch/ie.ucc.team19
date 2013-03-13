package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

public class InsertUser {
    public static void createStudent(StudentBean student) {
        String query;
        query = "INSERT INTO students VALUES" + "(NULL, ";
        query += "'" + student.getFirstName() +"', ";
        query += "'" + student.getLastName() + "', ";
        query += "'" + student.getEmail() + "', ";
        query += "'" + student.getPasswordHash() + "', ";
        query += "'" + student.getAddressLine1() + "', ";
        query += "'" + student.getAddressLine2() + "', ";
        query += "'" + student.getAddressLine3() + "', ";
        query += "'" + student.getCountry() + "', ";
        query += "'" + student.getTelephone() + "', ";
        query += "'" + student.getDateOfBirth() + "', ";
        query += "'" + student.getGender() + "', ";
        query += student.isAuthenticated() + ", ";
        query += "'" + student.getAuthString() + "', ";
        query += "'" + student.getDateRegistered() + "', "; 
        query += "'" + student.getCookieToken() + "', ";
        query += student.getEmailOptIn() + ")";

        new DBConnectionManager().Insert(query);
    }
}
