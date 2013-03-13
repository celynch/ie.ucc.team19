package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;

/**
 * 
 * @author Cormac
 *
 */
public class UpdateUser {
    
    /**
     * 
     * @param email
     * @param cookie_token
     */
    public void updateCookieToken(String email, String cookieToken) {
        String query = "UPDATE Students SET cookieToken = '" + cookieToken + "' WHERE email = '" + email + "'";
        new DBConnectionManager().Insert(query);
    }
    
    public void updateAuthString(String email, String authString) {
        String query = "UPDATE Students SET authString = '" + authString + "' WHERE email = '" + email + "'";
        new DBConnectionManager().Insert(query);
    }
}
