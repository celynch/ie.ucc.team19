package ie.ucc.team19.controllers.pages;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.service.InsertData;
import ie.ucc.team19.service.PropertiesReader;

/**
 * Controller for view for lecturer management.
 * @author Cormac
 */
public class AdminLecturersController extends AbstractController{

    /**
     * Accepts form submited for new lecturer, enters to db. Unauthorised
     * users sent to admin access page.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        String returnPage = "/adminLecturers.jsp";
        String pageTitle = "Lecturer Management";

        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()) {
            returnPage = "/admin.jsp";
            pageTitle = "Admin Access";
        }

        if(request.getParameter("addLec") != null) {
            LecturerBean lecturer = setupLecturer();
            new InsertData(connector).createLecturer(lecturer);
        }

        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("admin", true);
    }

    /**
     * Creates and populates LecturerBean from user form values describing
     * new lecturer.
     * @return LecturerBean for new lecturer.
     */
    private LecturerBean setupLecturer() {
        LecturerBean user = new LecturerBean();
        Map<String, String[]> userFormValues = request.getParameterMap();
        BeanUtilsBean beanManager = BeanUtilsBean.getInstance();
        try {
            beanManager.populate(user, userFormValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error populating LecturerBean");
            e.printStackTrace();
        }
        return user;
    }
}
