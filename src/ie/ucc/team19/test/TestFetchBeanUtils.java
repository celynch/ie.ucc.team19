package ie.ucc.team19.test;

//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ie.ucc.team19.dao.CategoryBean;
import ie.ucc.team19.dao.CommentBean;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.LecturerBean;
import ie.ucc.team19.dao.StudentBean;
import ie.ucc.team19.dao.VenueBean;
import ie.ucc.team19.service.FetchBeanUtils;
import java.util.*;
import org.junit.Test;

public class TestFetchBeanUtils{
    public static void main() {
        System.out.println("Testing FetchBeanUtils");
        try {
            //system under test
            new TestFetchBeanUtils().testGetCoursesByCourseId();
            new TestFetchBeanUtils().testGetCoursesByCourseCategory();
            new TestFetchBeanUtils().testGetLecturersByCourseId();
            new TestFetchBeanUtils().testGetLecturers();
            new TestFetchBeanUtils().testGetVenues();
            new TestFetchBeanUtils().testGetVenuesByCourseId();
            new TestFetchBeanUtils().testgetStudentByEmail();
            new TestFetchBeanUtils().testGetUnreviewedComments();
            new TestFetchBeanUtils().testGetCourses();
        } catch (Exception e) {
            System.out.println("Error testing FetchBeanUtils");
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCoursesByCourseId() throws Exception {
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
        CourseBean[] courses = new FetchBeanUtils(connector).getCourseByCourseId("1");

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(courses[0].getCourseId()==1);
    }

    @Test
    public void testGetCoursesByCourseCategory() throws Exception {
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
        CourseBean[] courses = new FetchBeanUtils(connector).getCoursesByCourseCategory("Culture");

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(courses[0].getCourseId()==1);
    }

    @Test
    public void testGetLecturersByCourseId() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("lecturerId", new String[]{"1"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        LecturerBean[] lecturers = new FetchBeanUtils(connector).getLecturersByCourseId("1");

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(lecturers[0].getLecturerId()==1);
    }

    @Test
    public void testGetLecturers() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("lecturerId", new String[]{"1"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        LecturerBean[] lecturers= new FetchBeanUtils(connector).getLecturers();

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(lecturers[0].getLecturerId()==1);
    }

    @Test
    public void testGetVenues() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("venueId", new String[]{"1"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        VenueBean[] venues = new FetchBeanUtils(connector).getVenues();

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(venues[0].getVenueId()==1);
    }

    @Test
    public void testGetVenuesByCourseId() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("venueId", new String[]{"1"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        VenueBean[] venues = new FetchBeanUtils(connector).getVenuesByCourseId("1");

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(venues[0].getVenueId()==1);
    }

    @Test
    public void testgetStudentByEmail() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("studentId", new String[]{"1"});
        row.put("enrollStartDate", new String[]{"2013-01-01"});
        row.put("enrollEndDate", new String[]{"2013-05-01"});
        row.put("courseStartDate", new String[]{"2013-06-01"});
        row.put("courseEndDate", new String[]{"2013-06-06"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        StudentBean student = new FetchBeanUtils(connector).getStudentByEmail("Culture");

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(student.getStudentId()=="1");
    }

    @Test
    public void testGetCourseCategories() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("courseCategory", new String[]{"Culture"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        CategoryBean categories = new FetchBeanUtils(connector).getCourseCategories();

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(categories.getCategoryTitles()[0]=="Culture");
    }

    @Test
    public void testGetUnreviewedComments() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("commentId", new String[]{"1"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        CommentBean[] comments = new FetchBeanUtils(connector).getUnreviewedComments();

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(comments[0].getCommentId()==1);
    }

    @Test
    public void testGetCourses() throws Exception {
        //mock objects
        DBConnectionManager connector = mock(DBConnectionManager.class);

        //Stubs
        ArrayList<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
        HashMap<String, String[]> row = new HashMap<String, String[]>();

        //initialise stubs
        row.put("courseId", new String[]{"1"});
        result.add(row);

        //define mock behaviour
        when(connector.Select(anyString(), any(Object[].class))).thenReturn(result);

        //test method
        CourseBean[] courses = new FetchBeanUtils(connector).getCourses();

        //verify
        verify(connector, atLeast(1)).Select(anyString(), any(Object[].class));
        assert(courses[0].getCourseId()==1);
    }
}
