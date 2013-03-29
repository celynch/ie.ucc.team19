package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.AdminBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.InsertData;
import ie.ucc.team19.service.PropertiesReader;

public class AdminManageController extends AbstractController{

    /**
     * Fetches beans from model for display in dashboard view.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        
        String returnPage = "/adminManage.jsp";
        String pageTitle = "Administration Management";
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()) {
            returnPage = "/admin.jsp";
            pageTitle = "Admin Access";
        }

        if(request.getParameter("addAdmin")!=null) {
            AdminBean admin = setupAdmin();
            new InsertData(connector).createAdmin(admin);
            request.setAttribute("adminAdded", "Admin Added: " + admin.getAdminName());
        }

        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("admin", true);
    }
    
    private AdminBean setupAdmin() {
        AdminBean admin = new AdminBean();
        admin.setAdminName(request.getParameter("adminName"));
        admin.setAdminPassword(request.getParameter("adminPassword"));
        admin.setEmail(request.getParameter("email"));
        return admin;
    }
}
