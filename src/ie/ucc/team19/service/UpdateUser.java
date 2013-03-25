package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;

/**
 * 
 * @author Cormac
 *
 */
public class UpdateUser {
    
    private DBConnectionManager connector;
    
    public UpdateUser(DBConnectionManager connector) {
        this.connector = connector;
    }
    
    /**
     * 
     * @param email
     * @param cookie_token
     */
    public void updateCookieToken(String email, String cookieToken) {
        String query = "UPDATE Students SET cookieToken = '"
                + cookieToken + "' WHERE email = '" + email + "'";
        connector.Insert(query);
    }
    
    public void updateAuthString(String email, String authString) {
        String query = "UPDATE Students SET authString = '"
                + authString + "' WHERE email = '" + email + "'";
        connector.Insert(query);
    }
    
    public void updatePasswordHash(String email, String passwordHash) {
        String query = "UPDATE Students SET passwordHash = '"
                + passwordHash + "' WHERE email = '" + email + "'";
        connector.Insert(query);
    }
    
    public void updateComment(String commentId) {
        String query = "UPDATE comments SET reviewed = "
                + true + " WHERE commentId = '" + commentId+ "'";
        connector.Insert(query);
    }
    
    public void expirePendingEnrolls() {
        String query = "DELETE FROM enrollments "
                     + "WHERE pending = true "
                     + "AND TIMESTAMPADD(HOUR, 2, enrollDate) < NOW()";
        connector.Insert(query);
    }
    
    public void expireUnverifiedStudents() {
        String query = "DELETE FROM students "
                     + "WHERE authenticated = false "
                     + "AND TIMESTAMPADD(DAY, 1, dateRegistered) < NOW()";
        connector.Insert(query);
    }
}
