package ie.ucc.team19.paypal;

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

public class IpnPaypalListenerServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        super.init();
    }

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
    	String paymentStatus = request.getParameter("payment_status");
    	//String paymentAmount = request.getParameter("mc_gross");
    	//String paymentCurrency = request.getParameter("mc_currency");
    	//String txnId = request.getParameter("txn_id");
    	String receiverEmail = request.getParameter("receiver_email");
    	//String payerEmail = request.getParameter("payer_email");
    	
     
    	// check notification validation
    	if (res.equals("VERIFIED")) {
    	    System.out.println("IPN VALID");
    	    if( (paymentStatus.equals("Completed"))
    	            && (receiverEmail.equals("celynch@gmail.com")) ) {
    	        System.out.println("paydirt!!");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
}