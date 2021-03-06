package ie.ucc.team19.service;

import ie.ucc.team19.dao.AdminBean;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.dao.VenueBean;

public class InsertData {
    private DBConnectionManager connector;
    
    public InsertData(DBConnectionManager connector) {
        this.connector = connector;
    }

    public void createAdmin(AdminBean admin) {
        String query = "INSERT INTO admins VALUES "
                + "(?,?,?)";

        Object[] params = {
                admin.getAdminName(),
                admin.getAdminPassword(),
                admin.getEmail()};

        connector.Insert(query, params);
    }

    public void createStudent(StudentBean student) {
        String query = "INSERT INTO students VALUES "
                + "(NULL, ?,?,?,?,?,?,?,?,?,?,?,0,?,?,?,0)";

        Object[] params = {
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword(),
                student.getAddressLine1(),
                student.getAddressLine2(),
                student.getAddressLine3(),
                student.getCountry(),
                student.getTelephone(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getAuthString(),
                student.getDateRegistered(),
                student.getCookieToken()};

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

    public void createCourse(CourseBean course) {
        String query = "INSERT INTO courses VALUES "
                + "(NULL,?,?,?,?,?,?,?,?,?)";

        Object[] params = {
                course.getCourseTitle(),
                course.getFee(),
                course.getSpaces(),
                course.getCourseCategory(),
                course.getContent(),
                course.getEnrollStartDate(),
                course.getEnrollEndDate(),
                course.getCourseStartDate(),
                course.getCourseEndDate()};

        connector.Insert(query, params);
    }

    public void updateCourse(CourseBean course) {
        String query = "UPDATE courses SET " +
        		"courseTitle=?,fee=?,spaces=?,courseCategory=?," +
        		"enrollStartDate=?,enrollEndDate=?," +
        		"courseStartDate=?,courseEndDate=?,content=?" +
        		"WHERE courseId=?";
        Object[] params = {course.getCourseTitle(), course.getFee(),
                course.getSpaces(), course.getCourseCategory(),
                course.getEnrollStartDate(), course.getEnrollEndDate(),
                course.getCourseStartDate(), course.getCourseEndDate(),
                course.getContent(), course.getCourseId()};
        connector.Insert(query, params);
    }

    public void setLecturer(int courseId, int lecturerId) {
        String query = "INSERT INTO teaches (courseId, lecturerId) VALUES (?,?)";
        Object[] params = {courseId, lecturerId};
        connector.Insert(query, params);
    }
    
    public void setVenue(int courseId, int venueId) {
        String query = "INSERT INTO courseLocations (courseId, venueId)  VALUES (?,?)";
        Object[] params = {courseId, venueId};
        connector.Insert(query, params);
    }
}
