package ie.ucc.team19.dao;

/**
 * Bean to represent an admin 
 * @author Cormac
 */
public class AdminBean implements UserBean {
    private String adminName;
    private String adminPassword;
    private String email;
    private boolean admin = true;

    public AdminBean() {
        
    }

    public String getUniqueId() {
        return adminName;
    }

    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
