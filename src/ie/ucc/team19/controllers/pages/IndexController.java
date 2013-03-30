package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.*;
import ie.ucc.team19.service.FetchBeanUtils;
import ie.ucc.team19.service.PropertiesReader;

/**
 *Controller class to handle request to view the Front Page
 * @author Cormac
 */
public class IndexController extends AbstractController {

    /**
     * Fetches Category Names for front page category navigation view.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        CategoryBean categories = fetcher.getCourseCategories();
        request.setAttribute("categories", categories);

        setReturnPage("/index.jsp");
        request.setAttribute("pageTitle", "Welcome");
    }
}
