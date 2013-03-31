package ie.ucc.team19.test;

//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.service.EnrollStudent;
import java.util.*;
import javax.servlet.http.*;
import org.junit.Test;

public class TestEnrollStudent{
    public static void main() {
        System.out.println("Testing EnrollStudent");
        try {
            //system under test
            new TestEnrollStudent().testDetectConflicts();
        } catch (Exception e) {
            System.out.println("Error testing DetectConflicts");
            e.printStackTrace();
        }
        
        try {
            new TestEnrollStudent().testenrollToCourse();
        } catch (Exception e) {
            System.out.println("Error testing enrollToCourse");
            e.printStackTrace();
        }

        try {
            new TestEnrollStudent().testUnEnrollFromCourse();
        } catch (Exception e) {
            System.out.println("Error testing unEnrollFromCourse");
            e.printStackTrace();
        }

        try {
            new TestEnrollStudent().testGetCoursesByStudentId();
        } catch (Exception e) {
            System.out.println("Error testing getCoursesByStudentId");
            e.printStackTrace();
        }
    }

    @Test
    public void testDetectConflicts() throws Exception {
        //mock objects
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        StudentBean student = new StudentBean();
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        student.setStudentId("1");
        row.put("courseId", new String[]{"1"});
        row.put("enrollStartDate", new String[]{"2013-01-01"});
        row.put("enrollEndDate", new String[]{"2013-05-01"});
        row.put("courseStartDate", new String[]{"2013-06-01"});
        row.put("courseEndDate", new String[]{"2013-06-06"});
        result.add(row);

        //define mock behaviour
        when(request.getParameter("username")).thenReturn("me");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(student);
        when(request.getParameter("enrollCourseId")).thenReturn("1");
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        new EnrollStudent(connector).detectConflicts(request);
    }
    
    @Test
    public void testenrollToCourse() throws Exception {
      //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        new EnrollStudent(connector).enrollToCourse(
                "1", "1", false, false, false, false, "2013-03-20");
    }

    @Test
    public void testUnEnrollFromCourse() throws Exception {
      //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        new EnrollStudent(connector).unEnrollFromCourse("1", "1");
    }
    
    @Test
    public void testGetCoursesByStudentId() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("courseId", new String[]{"1"});
        row.put("enrollStartDate", new String[]{"2013-01-01"});
        row.put("enrollEndDate", new String[]{"2013-05-01"});
        row.put("courseStartDate", new String[]{"2013-06-01"});
        row.put("courseEndDate", new String[]{"2013-06-06"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        new EnrollStudent(connector).getCoursesByStudentId("1");
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
    }
    
}
