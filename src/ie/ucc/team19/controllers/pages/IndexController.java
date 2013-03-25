package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;

/**
 *Controller class to handle request to view the Front Page
 * @author Cormac
 */
public class IndexController extends AbstractController {

    /**
     * 
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        CategoryBean categories = fetcher.getCourseCategories();

        setReturnPage("/index.jsp");
        request.setAttribute("pageTitle", "Welcome");
        request.setAttribute("categories", categories);
    }
}
