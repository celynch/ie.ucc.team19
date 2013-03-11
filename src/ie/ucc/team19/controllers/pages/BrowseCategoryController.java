package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.service.FetchBean;

public class BrowseCategoryController extends AbstractController{
    public void execute() {
        this.setReturnPage("/browseCategory.jsp");
        String category = this.getRequest().getParameter("category");
        this.getRequest().setAttribute("pageTitle", "Courses in " + category + " | UCC Summer Courses");
        CourseBean[] categoryCourses = new FetchBean().getCourseByCategory(category);
        this.getRequest().setAttribute("categoryCourses", categoryCourses);
    }
}
