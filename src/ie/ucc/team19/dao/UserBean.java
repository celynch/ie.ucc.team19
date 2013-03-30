package ie.ucc.team19.dao;

/**
 * Interface for Beans representing users. Session scoped UserBeans are
 * used to track the logged in user, whether student or admin. 
 * @author Cormac
 */
public interface UserBean {
    public String getUniqueId();
    public boolean isAdmin();
}
