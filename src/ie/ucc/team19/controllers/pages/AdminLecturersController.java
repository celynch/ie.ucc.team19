package ie.ucc.team19.controllers.pages;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.service.InsertData;

public class AdminLecturersController extends AbstractController{

    /**
     * 
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();

        if(request.getParameter("addLec") != null) {
            LecturerBean lecturer = setupLecturer();
            new InsertData(connector).createLecturer(lecturer);
        }

        setReturnPage("/adminLecturers.jsp");
        request.setAttribute("pageTitle", "Lecturer Management");
        request.setAttribute("admin", true);
    }
    
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
