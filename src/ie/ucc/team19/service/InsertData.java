package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.dao.VenueBean;

public class InsertData {
    private DBConnectionManager connector;
    
    public InsertData(DBConnectionManager connector) {
        this.connector = connector;
    }
    
    public void createStudent(StudentBean student) {
        String query = "INSERT INTO students VALUES "
                + "(NULL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Object[] params = {
                student.getFirstName(),
                student.getLastName(),
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

    public void createLecturer(LecturerBean lecturer) {
        String query = "INSERT INTO lecturers VALUES "
                + "(NULL, ?,?,?,?,?,?,?,?,?,?)";

        Object[] params = {
                lecturer.getFirstName(),
                lecturer.getLastName(),
                lecturer.getEmail(),
                lecturer.getLecturerTitle(),
                lecturer.getPosition(),
                lecturer.getAddressLine1(),
                lecturer.getAddressLine2(),
                lecturer.getAddressLine3(),
                lecturer.getCountry(),
                lecturer.getTelephone()};

        connector.Insert(query, params);
    }

    public void createVenue(VenueBean venue) {
        String query = "INSERT INTO venues VALUES "
                + "(NULL, ?,?,?,?,?,?," + venue.isOnCampus() +")";

        Object[] params = {
                venue.getVenueRoom(),
                venue.getVenueBuilding(),
                venue.getAddressLine1(),
                venue.getAddressLine2(),
                venue.getAddressLine3(),
                venue.getCapacity()};

        connector.Insert(query, params);
    }
}
