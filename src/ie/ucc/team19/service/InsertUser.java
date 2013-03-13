package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

public class InsertUser {
    public static void createStudent(StudentBean student) {
        String query;
        query = "INSERT INTO Students VALUES" + "(NULL, ";
        query += "'" + student.getFirst_name() +"', ";
        query += "'" + student.getLast_name() + "', ";
        query += "'" + student.getEmail() + "', ";
        query += "'" + student.getPassword_hash() + "', ";
        query += "'" + student.getAddress_line1() + "', ";
        query += "'" + student.getAddress_line2() + "', ";
        query += "'" + student.getAddress_line3() + "', ";
        query += "'" + student.getCountry() + "', ";
        query += "'" + student.getTelephone() + "', ";
        query += "'" + student.getDate_of_birth() + "', ";
        query += "'" + student.getGender() + "', ";
        query += student.isAuthenticated() + ", ";
        query += "'" + student.getAuth_string() + "', ";
        query += "'" + student.getDate_ac_created() + "', "; 
        query += "'" + student.getCookie_token() + "')";

        new DBConnectionManager().Insert(query);
    }
}
