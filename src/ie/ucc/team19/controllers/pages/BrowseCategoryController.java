package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;

/**
 * Controller class to handle request to browse courses of a specific
 * category.
 * @author Cormac
 */
public class BrowseCategoryController extends AbstractController{
    /**
     * Fetches beans to represent courses belonging to user selected 
     * category from model, passes to browseCategory view request scoped. 
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        String category = request.getParameter("category");
        
        setReturnPage("/browseCategory.jsp");
        request.setAttribute("pageTitle", "Courses in " + category);
        CourseBean[] categoryCourses = new FetchBeanUtils(connector).getCoursesByCourseCategory(category);
        request.setAttribute("categoryCourses", categoryCourses);
    }
}
