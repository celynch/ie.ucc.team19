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
    public static void updateCookieToken(String email, String cookie_token) {
        String query = "UPDATE Students SET cookie_token = '" + cookie_token + "' WHERE email = '" + email + "'";
        new DBConnectionManager().Insert(query);
    }
}
