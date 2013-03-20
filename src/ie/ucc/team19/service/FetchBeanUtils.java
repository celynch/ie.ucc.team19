package ie.ucc.team19.service;

import ie.ucc.team19.dao.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;


public class FetchBeanUtils {
    private DBConnectionManager connector;
    public FetchBeanUtils(DBConnectionManager connector) {
        this.connector = connector;
    }

    public CourseBean[] getCourseByCourseId(String course_id) {
        course_id = course_id.replace("'", "''");
        String query = "SELECT * "
                     + "FROM courses "
                     + "WHERE courseId = \'"
                     + course_id + "\'";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        CourseBean[] courses = new CourseBean[resultTable.size()];
        courses = (CourseBean[]) sqlBeanPopulate(courses, resultTable);
        return courses;
    }

    public CourseBean[] getCoursesByCourseCategory(String courseCategory) {
        courseCategory = courseCategory.replace("'", "''");
        String query = "SELECT * "
                     + "FROM courses "
                     + "WHERE courseCategory = \'"
                     + courseCategory + "\'";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        CourseBean[] courses = new CourseBean[resultTable.size()];
        courses = (CourseBean[]) sqlBeanPopulate(courses, resultTable);
        return courses;
    }

    public LecturerBean[] getLecturersByCourseId(String courseId) {
        courseId = courseId.replace("'", "''");
        String query = "SELECT * "
                     + "FROM lecturers "
                     + "WHERE lecturerId IN ("
                         + "SELECT lecturerId "
                         + "FROM teaches "
                         + "WHERE courseId >= \'"
                         + courseId + "\')";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        LecturerBean[] lecturers = new LecturerBean[resultTable.size()];
        lecturers = (LecturerBean[]) sqlBeanPopulate(lecturers, resultTable);
        return lecturers;
    }

    public VenueBean[] getVenuesByCourseId(String courseId) {
        courseId = courseId.replace("'", "''");
        String query = "SELECT * "
                     + "FROM venues "
                     + "WHERE venueId IN ("
                         + "SELECT venueId "
                         + "FROM courseLocations "
                         + "WHERE courseId = \'"
                         + courseId + "\')";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        VenueBean[] venues = new VenueBean[resultTable.size()];
        venues = (VenueBean[]) sqlBeanPopulate(venues, resultTable);
        return venues;
    }

    private ArrayList<Map<String, String[]>> fetchTable(String query) {
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query);
        return resultTable;
    }

    Object[] sqlBeanPopulate(Object[] beans, ArrayList<Map<String, String[]>> resultTable) {
        int beanNum = 0;
        Class<?> ofArray = beans.getClass().getComponentType();
        for(Map<String, String[]> result : resultTable) {
            try {
                beans[beanNum] = ofArray.newInstance();
            } catch (InstantiationException | IllegalAccessException e1) {
                System.out.println("Error determining bean type");
                e1.printStackTrace();
            }
            try {
                new BeanUtilsBean().populate(beans[beanNum], result);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println("Error populating CourseBean");
                e.printStackTrace();
            }
            beanNum++;
        }
        return beans;
    }

    public StudentBean getStudentByEmail(String email) {
        String query = "SELECT * "
                     + "FROM students "
                     + "WHERE email = \'"
                     + email + "\'";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        StudentBean[] student = new StudentBean[1];
        student = (StudentBean[]) sqlBeanPopulate(student, resultTable);
        return student[0];
    }

    public CategoryBean getCourseCategories() {
        CategoryBean categories = new CategoryBean();
        String query = "SELECT DISTINCT courseCategory "
                     + "FROM courses";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        String[] categoryList = new String[resultTable.size()];
        int catNumber = 0;
        for(Map<String, String[]> row : resultTable) {
            categoryList[catNumber] = row.get("courseCategory")[0];
            catNumber++;
        }
        categories.setCategoryTitles(categoryList);
        return categories;
    }
}
