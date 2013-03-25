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
        String query = "SELECT * "
                     + "FROM courses "
                     + "WHERE courseId = ?";
        Object[] params = {course_id};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        CourseBean[] courses = new CourseBean[resultTable.size()];
        courses = (CourseBean[]) sqlBeanPopulate(courses, resultTable);
        return courses;
    }

    public CourseBean[] getCoursesByCourseCategory(String courseCategory) {
        String query = "SELECT * "
                     + "FROM courses "
                     + "WHERE courseCategory = ?";
        Object[] params = {courseCategory};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        CourseBean[] courses = new CourseBean[resultTable.size()];
        courses = (CourseBean[]) sqlBeanPopulate(courses, resultTable);
        return courses;
    }

    public LecturerBean[] getLecturersByCourseId(String courseId) {
        String query = "SELECT * "
                     + "FROM lecturers "
                     + "WHERE lecturerId IN ("
                         + "SELECT lecturerId "
                         + "FROM teaches "
                         + "WHERE courseId = ?)";
        Object[] params = {courseId};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        LecturerBean[] lecturers = new LecturerBean[resultTable.size()];
        lecturers = (LecturerBean[]) sqlBeanPopulate(lecturers, resultTable);
        return lecturers;
    }
    
    public LecturerBean[] getLecturers() {
        String query = "SELECT * FROM lecturers";
        Object[] params = {};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        LecturerBean[] lecturers = new LecturerBean[resultTable.size()];
        lecturers = (LecturerBean[]) sqlBeanPopulate(lecturers, resultTable);
        return lecturers;
    }

    public VenueBean[] getVenuesByCourseId(String courseId) {
        String query = "SELECT * "
                     + "FROM venues "
                     + "WHERE venueId IN ("
                         + "SELECT venueId "
                         + "FROM courseLocations "
                         + "WHERE courseId = ?)";
        Object[] params = {courseId};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        VenueBean[] venues = new VenueBean[resultTable.size()];
        venues = (VenueBean[]) sqlBeanPopulate(venues, resultTable);
        return venues;
    }

    /*private ArrayList<Map<String, String[]>> fetchTable(String query, ) {
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        return resultTable;
    }*/

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
                     + "WHERE email = ?";
        Object[] params = {email};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        StudentBean[] student = new StudentBean[1];
        student = (StudentBean[]) sqlBeanPopulate(student, resultTable);
        return student[0];
    }

    public CategoryBean getCourseCategories() {
        CategoryBean categories = new CategoryBean();
        String query = "SELECT DISTINCT courseCategory "
                     + "FROM courses";
        Object[] params = {};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        String[] categoryList = new String[resultTable.size()];
        int catNumber = 0;
        for(Map<String, String[]> row : resultTable) {
            categoryList[catNumber] = row.get("courseCategory")[0];
            catNumber++;
        }
        categories.setCategoryTitles(categoryList);
        return categories;
    }
    
    public CommentBean[] getUnreviewedComments() {
        String query = "SELECT commentId, students.studentId, email, firstName, subject, messageText, reviewed "
                     + "FROM comments, students "
                     + "WHERE comments.studentId = students.studentId "
                     + "AND reviewed = false";
        Object[] params = {};
        ArrayList<Map<String, String[]>> resultTable = connector.Select(query, params);
        CommentBean[] comments = new CommentBean[resultTable.size()];
        comments = (CommentBean[]) sqlBeanPopulate(comments, resultTable);
        return comments;
    }
}
