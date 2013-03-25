package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CategoryBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;

/**
 * Controller class to handle request to browse courses by category.
 * @author Cormac
 */
public class BrowseAllCategoriesController extends AbstractController{

    /**
     * Fetches bean to represent category names from the model, passes to
     * browseAllCategories as request scoped.
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        
        setReturnPage("/browseAllCategories.jsp");
        request.setAttribute("pageTitle", "Browse Categories");
        CategoryBean categories = new FetchBeanUtils(connector).getCourseCategories();
        request.setAttribute("categories", categories);
    }
}
