package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CategoryBean;
import ie.ucc.team19.service.FetchBean;

public class BrowseAllCategoriesController extends AbstractController{
    public void execute() {
        setReturnPage("/browseAllCategories.jsp");
        getRequest().setAttribute("pageTitle", "Browse Categories | UCC Summer Courses");
        CategoryBean categories = new FetchBean().getCourseCategories();
        getRequest().setAttribute("categories", categories);
    }
}
