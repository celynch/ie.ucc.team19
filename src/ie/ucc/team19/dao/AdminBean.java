package ie.ucc.team19.dao;

public class AdminBean extends UserBean {
    private String adminName;
    private String adminPassword;
    private String email;

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

    
}
