package ie.ucc.team19.service;

import ie.ucc.team19.dao.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;


public class FetchBean {
    public CourseBean[] getCourseById(String course_id) {
        course_id = course_id.replace("'", "''");
        String query = "SELECT * FROM Courses WHERE course_id = \'"
                + course_id + "\'";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        CourseBean[] courses = new CourseBean[resultTable.size()];
        courses = (CourseBean[]) sqlBeanPopulate(courses, resultTable);
        return courses;
    }
    
    public LecturerBean[] getCourseLecturers(String course_id) {
        course_id = course_id.replace("'", "''");
        String query = "SELECT * FROM Lecturers WHERE lecturer_id IN (SELECT lecturer_id FROM Teaches WHERE course_id >= \'"
                + course_id + "\')";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        LecturerBean[] lecturers = new LecturerBean[resultTable.size()];
        lecturers = (LecturerBean[]) sqlBeanPopulate(lecturers, resultTable);
        return lecturers;
    }

    public VenueBean[] getCourseVenues(String course_id) {
        course_id = course_id.replace("'", "''");
        String query = "SELECT * FROM Venues WHERE venue_id IN (SELECT venue_id FROM course_locations WHERE course_id = \'"
                + course_id + "\')";
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        VenueBean[] venues = new VenueBean[resultTable.size()];
        venues = (VenueBean[]) sqlBeanPopulate(venues, resultTable);
        return venues;
    }

    private ArrayList<Map<String, String[]>> fetchTable(String query) {
        ArrayList<Map<String, String[]>> resultTable = new DBConnectionManager().Select(query);
        return resultTable;
    }

    private Object[] sqlBeanPopulate(Object[] beans, ArrayList<Map<String, String[]>> resultTable) {

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

    public CategoryBean getCourseCategories() {
        CategoryBean categories = new CategoryBean();
        String query = "SELECT DISTINCT course_category FROM Courses";
        System.out.println(query);
        ArrayList<Map<String, String[]>> resultTable = fetchTable(query);
        String[] categoryList = new String[resultTable.size()];
        int catNumber = 0;
        for(Map<String, String[]> row : resultTable) {
            categoryList[catNumber] = row.get("course_category")[0];
            catNumber++;
        }
        categories.setCategoryTitles(categoryList);
        for(String foo : categoryList) {
            System.out.println(foo);
        }
        return categories;
    }
}
