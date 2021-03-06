package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.UserBean;
import ie.ucc.team19.dao.VenueBean;
import ie.ucc.team19.service.InsertData;
import ie.ucc.team19.service.PropertiesReader;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * Controller for view for venue management.
 * @author Cormac
 */
public class AdminVenuesController extends AbstractController{

    /**
     * Accepts form submited for new venues, enters to db. Unauthorised
     * users sent to admin access page.
     */
    public void execute() {
        PropertiesReader properties = (PropertiesReader)
                request.getSession().getServletContext().getAttribute("properties");
        DBConnectionManager connector = new DBConnectionManager(properties);
        String returnPage = "/adminVenues.jsp";
        String pageTitle = "Venue Management";

        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()) {
            returnPage = "/admin.jsp";
            pageTitle = "Admin Access";
        }

        if(request.getParameter("addVen") != null) {
            VenueBean venue = setupVenue();
            new InsertData(connector).createVenue(venue);
        }

        setReturnPage(returnPage);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("admin", true);
    }

    /**
     * Creates and populates VenueBean from user form values describing
     * new venue.
     * @return VenueBean for new venue.
     */
    private VenueBean setupVenue() {
        VenueBean venue = new VenueBean();
        Map<String, String[]> userFormValues = request.getParameterMap();
        BeanUtilsBean beanManager = BeanUtilsBean.getInstance();
        try {
            beanManager.populate(venue, userFormValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error populating VenueBean");
            e.printStackTrace();
        }
        return venue;
    }
}
