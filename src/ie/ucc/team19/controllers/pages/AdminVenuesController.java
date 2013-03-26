package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.VenueBean;
import ie.ucc.team19.service.InsertData;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

public class AdminVenuesController extends AbstractController{

    /**
     * 
     */
    public void execute() {
        DBConnectionManager connector = new DBConnectionManager();

        if(request.getParameter("addVen") != null) {
            VenueBean venue = setupVenue();
            new InsertData(connector).createVenue(venue);
        }

        setReturnPage("/adminVenues.jsp");
        request.setAttribute("pageTitle", "Venue Management");
        request.setAttribute("admin", true);
    }
    
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
