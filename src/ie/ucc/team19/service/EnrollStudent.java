package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;

public class EnrollStudent {
    public void enrollToCourse(String email, String courseId) {
        String query = "INSERT INTO enrollments () VALUES ('" + courseId + "', '" + email + "'";

        new DBConnectionManager().Insert(query);
    }
}
