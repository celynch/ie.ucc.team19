package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.service.FetchBean;

public class BrowseCategoryController extends AbstractController{
    public void execute() {
        setReturnPage("/browseCategory.jsp");
        String category = this.getRequest().getParameter("category");
        getRequest().setAttribute("pageTitle", "Courses in " + category);
        CourseBean[] categoryCourses = new FetchBean().getCoursesByCourseCategory(category);
        getRequest().setAttribute("categoryCourses", categoryCourses);
    }
}
