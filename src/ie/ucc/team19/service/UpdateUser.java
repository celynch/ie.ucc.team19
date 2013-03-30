package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;

/**
 * Class for handling updates to users and enrollments  
 * @author Cormac
 */
public class UpdateUser {
    
    private DBConnectionManager connector;

    /**
     * Constructor for UpdateUser
     * @param connector
     */
    public UpdateUser(DBConnectionManager connector) {
        this.connector = connector;
    }

    /**
     * The student identified by email gets a new cookieToken string,
     * rendering old cookies invalid for login.
     * @param email
     * @param cookie_token
     */
    public void updateCookieToken(String email, String cookieToken) {
        String query = "UPDATE students SET cookieToken = '"
                + cookieToken + "' WHERE email = ?";
        Object[] params = {email};
        connector.Insert(query, params);
    }

    /**
     * The student identified by email gets a new authString,
     * rendering old authStrings invalid for activation / verfication. 
     * @param email
     * @param authString
     */
    public void updateAuthString(String email, String authString) {
        String query = "UPDATE Students SET authString = '"
                + authString + "' WHERE email = ?";
        Object[] params = {email};
        connector.Insert(query, params);
    }

    /**
     * On complete of password reset request, student identified by email
     * gets new password
     * @param email - unique key of student
     * @param password - replacement password
     */
    public void updatePassword(String email, String password) {
        String query = "UPDATE Students SET password = '"
                + password + "' WHERE email = ?";
        Object[] params = {email};
        connector.Insert(query, params);
    }

    /**
     * On response from admin, existing comment is tagged as reviewed.
     * @param commentId - the comment which received a response.
     */
    public void updateComment(String commentId) {
        String query = "UPDATE comments SET reviewed = "
                + true + " WHERE commentId = ?";
        Object[] params = {commentId};
        connector.Insert(query, params);
    }

    /**
     * Scheduled task, deletes enrollment to course which is still pending
     * after 2 hours.
     */
    public void expirePendingEnrolls() {
        String query = "DELETE FROM enrollments "
                     + "WHERE pending = true "
                     + "AND TIMESTAMPADD(HOUR, 2, enrollDate) < NOW()";
        Object[] params = {};
        connector.Insert(query, params);
    }

    /**
     * Scheduled task, deletes student account if not activated within 24
     * hours.
     */
    public void expireUnverifiedStudents() {
        String query = "DELETE FROM students "
                     + "WHERE authenticated = false "
                     + "AND TIMESTAMPADD(DAY, 1, dateRegistered) < NOW()";
        Object[] params = {};
        connector.Insert(query, params);
    }

    /**
     * On user edit of details new details from StudentBean are reflected
     * in the db  
     * @param student - StudentBean to update db with.
     */
    public void updateStudent(StudentBean student) {
        String query = "UPDATE students SET firstName = ?, lastName = ?,"
                + "addressLine1 = ?, addressLine2 = ?, addressLine3 = ?, country = ?"
                + " WHERE studentId = ?";
        Object[] params = {student.getFirstName(), student.getLastName(),
                student.getAddressLine1(), student.getAddressLine2(), student.getAddressLine3(),
                student.getCountry(), student.getStudentId()};
        connector.Insert(query, params);
    }

    /**
     * On receipt of payment transaction, ipn listener calls this method
     * to update status of payment for this enrollment.
     * @param customFields - fields returned from paypal, ids student, course, payment
     */
    public void updateEnrollment(String[] customFields) {
        String payment = customFields[2].equals("1") ? "paidDeposit": "paidFee";
        String query = "UPDATE enrollments SET pending = 0, " + payment + " = 1"
                + " WHERE studentId = ? AND courseId =  ?";
        Object[] params = {customFields[0], customFields[1]};
        connector.Insert(query, params);
    }
}
