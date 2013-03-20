package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CategoryBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.FetchBeanUtils;

public class BrowseAllCategoriesController extends AbstractController{
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        
        setReturnPage("/browseAllCategories.jsp");
        getRequest().setAttribute("pageTitle", "Browse Categories");
        CategoryBean categories = new FetchBeanUtils(connector).getCourseCategories();
        getRequest().setAttribute("categories", categories);
    }
}
