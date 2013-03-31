package ie.ucc.team19.test;

//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
import ie.ucc.team19.service.FormValidater;
import java.util.*;
import org.junit.Test;


public class TestFormValidater {
    public static void main() {
        System.out.println("Testing FormValidater");
        try {
            //system under test
            new TestFormValidater().testCheckForm();
        } catch (Exception e) {
            System.out.println("Error testing FormValidater");
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckForm() throws Exception {
        //mock objects
        //Stubs
        HashMap<String, String[]> row = new HashMap<String, String[]>();
    
        //initialise stubs
        row.put("firstName", new String[]{"Cormac"});
        row.put("lastName", new String[]{"Lynch"});
        row.put("email", new String[]{"celynch@gmail.com"});
        row.put("password", new String[]{"123"});
        row.put("addressLine1", new String[]{"a"});
        row.put("addressLine2", new String[]{"b"});
        row.put("addressLine3", new String[]{"c"});
        row.put("country", new String[]{"Ireland"});
        row.put("telephone", new String[]{"123456"});
        row.put("gender", new String[]{"M"});
        row.put("courseTitle", new String[]{"The Romans"});
        row.put("fee", new String[]{"200"});
        row.put("courseCategory", new String[]{"History"});
        row.put("courseId", new String[]{"1"});
        row.put("enrollStartDate", new String[]{"2013-01-01"});
        row.put("enrollEndDate", new String[]{"2013-05-01"});
        row.put("courseStartDate", new String[]{"2013-06-01"});
        row.put("courseEndDate", new String[]{"2013-06-06"});
        row.put("addCourse", null);
        row.put("updateCourse", null);
    
        //define mock behaviour
    
        //test method
        FormValidater validater = new FormValidater();
        String error = validater.checkForm(row);
    
        //verify
        //verify(validater, atLeast(1)).checkForm(row);
        assert(error==null);
    }
}
