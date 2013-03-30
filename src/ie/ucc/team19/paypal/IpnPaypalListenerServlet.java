package ie.ucc.team19.paypal;

import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.service.PropertiesReader;
import ie.ucc.team19.service.UpdateUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to respond to paypal Instant Payment Notifications.
 * Conforms to paypal API.
 * @author Cormac
 */
public class IpnPaypalListenerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Initialises servlet instance.
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Processes received ipns from paypal, sends response with mirrored
     * fields, gets confirmation that ipn came from paypal and updates db.
     * @param request - incoming request
     * @param response - mirrored POST repsonse
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // read post from PayPal system and add 'cmd'
    	Enumeration<String> en = request.getParameterNames();
    	StringBuffer strBuffer = new StringBuffer("cmd=_notify-validate");
    	String paramName;
    	String paramValue;
    	while (en.hasMoreElements()) {
    		paramName = (String) en.nextElement();
    		paramValue = request.getParameter(paramName);
    		//System.out.println(paramName + " = " + paramValue);
    		strBuffer.append("&").append(paramName).append("=")
    				.append(URLEncoder.encode(paramValue, "UTF-8"));
    	}

    	// post back to PayPal system to validate
    	// NOTE: change http: to https: in the following URL to verify using SSL (for increased security).
    	// using HTTPS requires either Java 1.4 or greater, or Java Secure Socket Extension (JSSE)
    	// and configured for older versions.
    	URL u = new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
    	//URL u = new URL("https://www.paypal.com/cgi-bin/webscr");
    	HttpsURLConnection uc = (HttpsURLConnection) u.openConnection();
    	uc.setDoOutput(true);
    	uc.setRequestProperty("Content-Type",
    			"application/x-www-form-urlencoded");
    	uc.setRequestProperty("Host", "www.paypal.com");
    	PrintWriter pw = new PrintWriter(uc.getOutputStream());
    	pw.println(strBuffer.toString());
    	pw.close();

    	BufferedReader in = new BufferedReader(new InputStreamReader(
    			uc.getInputStream()));
    	String res = in.readLine();
    	in.close();

    	// assign posted variables to local variables
    	//String itemName = request.getParameter("item_name");
    	//String itemNumber = request.getParameter("item_number");
    	//String paymentAmount = request.getParameter("mc_gross");
    	//String paymentCurrency = request.getParameter("mc_currency");
    	//String txnId = request.getParameter("txn_id");
    	//String payerEmail = request.getParameter("payer_email");
    	String[] customFields = request.getParameter("custom").split("-");
    	String receiverEmail = request.getParameter("receiver_email");

    	// check notification validation
    	if (res.equals("VERIFIED")) {
    	    System.out.println("IPN VALID");
    	    PropertiesReader properties = (PropertiesReader)
    	            request.getSession().getServletContext().getAttribute("properties");
    	    /*paymentStatus.equals("Completed")*/
    	    if( receiverEmail.equals(properties.getPaypalEmailId()) ) {
    	        System.out.println("IPN received");
    	        DBConnectionManager connector = new DBConnectionManager(properties);
    	        new UpdateUser(connector).updateEnrollment(customFields);
    	    }
    		// check that paymentStatus=Completed
    		// check that txnId has not been previously processed
    		// check that receiverEmail is your Primary PayPal email
    		// check that paymentAmount/paymentCurrency are correct
    		// process payment
    	} else if (res.equals("INVALID")) {
    	    System.out.println("IPN Error: INVALID");
    		// log for investigation
    	} else {
    		System.out.println("IPN Error");
    	}
    }

    /**
     * GET requests handled by processRequest
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * POST requests handled by processRequest
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
