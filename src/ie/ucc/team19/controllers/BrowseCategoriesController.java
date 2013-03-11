package ie.ucc.team19.controllers;

import ie.ucc.team19.dao.CategoryBean;
import ie.ucc.team19.service.FetchBean;

public class BrowseCategoriesController extends AbstractController{
    public void execute() {
        this.setReturnPage("/browseCategories.jsp");
        this.getRequest().setAttribute("pageTitle", "Browse Categories | UCC Summer Courses");
        CategoryBean categories = new FetchBean().getCourseCategories();
        this.getRequest().setAttribute("categories", categories);
    }
}
