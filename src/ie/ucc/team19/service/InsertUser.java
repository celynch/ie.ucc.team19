package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

public class InsertUser {
    private DBConnectionManager connector;
    
    public InsertUser(DBConnectionManager connector) {
        this.connector = connector;
    }
    
    public void createStudent(StudentBean student) {
        String query = "INSERT INTO students VALUES"
                + "(NULL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Object[] params = {student.getFirstName(), student.getLastName(),
                student.getEmail(),
                student.getPasswordHash(),
                student.getAddressLine1(),
                student.getAddressLine2(),
                student.getAddressLine3(),
                student.getCountry(),
                student.getTelephone(),
                student.getDateOfBirth(),
                student.getGender(),
                student.isAuthenticated(),
                student.getAuthString(),
                student.getDateRegistered(),
                student.getCookieToken(),
                student.getEmailOptIn()};

        connector.Insert(query, params);
    }
}
