package ie.ucc.team19.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.EnrollmentBean;
import ie.ucc.team19.dao.StudentBean;

public class EnrollStudent {
    private DBConnectionManager connector;
    private static final long DAY_OFFSET = 1000 *60 * 60 * 24;
    
    public EnrollStudent(DBConnectionManager connector) {
        this.connector = connector;
    }
    
    public void enrollToCourse(String courseId, String studentId, boolean pending,
            boolean paidDeposit, boolean paidFee, boolean issuedRefund) {
        String query = "INSERT INTO enrollments (courseId, studentId, pending, paidDeposit, paidFee, issuedRefund)"
            + " VALUES ('"
            + courseId + "', '"
            + studentId + "', '"
            + 1  + "', '"
            + 0 + "', '"
            + 0 + "', '"
            + 0 + "')";
        connector.Insert(query);
    }

    private EnrollmentBean[] getEnrollmentsByStudentId(String studentId) {
        String query = "SELECT * "
                     + "FROM enrollments "
                     + "WHERE studentId = '" + studentId + "'";
        ArrayList<Map<String,String[]>> resultTable = connector.Select(query);
        EnrollmentBean[] enrollments = new EnrollmentBean[resultTable.size()];
        enrollments = (EnrollmentBean[]) new FetchBeanUtils(connector).sqlBeanPopulate(enrollments, resultTable);
        return enrollments;
    }

    private CourseBean[] getCoursesByStudentId(String studentId) {
        String query = "SELECT * " +
        		       "FROM courses " +
        		       "WHERE courseId IN (" +
        		           "SELECT courseId " +
        		           "FROM enrollments " +
        		           "WHERE studentId = '" + studentId + "') " +
	          		  "ORDER BY courseStartDate";
        ArrayList<Map<String,String[]>> resultTable = connector.Select(query);
        CourseBean[] courses = new CourseBean[resultTable.size()];
        courses = (CourseBean[]) new FetchBeanUtils(connector).sqlBeanPopulate(courses, resultTable);
        return courses;
    }
    
    public static boolean detectConflicts(HttpServletRequest request, DBConnectionManager connector) {
        // setup DAOs
        EnrollStudent enroller = new EnrollStudent(connector);
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        // get params from request & session
        StudentBean student = (StudentBean) request.getSession().getAttribute("user");
        String studentId = student.getStudentId();
        String enrollCourseId = request.getParameter("enrollCourseId");
        System.out.println("course selected: " + enrollCourseId);

        // get data from db
        enroller.enrollToCourse(enrollCourseId, studentId, true, false, false, false);
        CourseBean[] courses = enroller.getCoursesByStudentId(studentId);
        EnrollmentBean[] enrollments = enroller.getEnrollmentsByStudentId(studentId);
        CourseBean[] enrollCourse = fetcher.getCourseByCourseId(enrollCourseId);

        // algo for detecting & displaying date conflicts
        Date earliestStart = enrollCourse[0].getCourseStartDate();
        Date latestEnd = enrollCourse[0].getCourseEndDate();
        for(CourseBean course : courses) {
            Date courseStart = course.getCourseStartDate();
            Date courseEnd = course.getCourseEndDate();
            if(courseStart.compareTo(earliestStart) < 0 ) {
                earliestStart = courseStart;
            }
            if(courseEnd.compareTo(latestEnd) > 0) {
                latestEnd = courseEnd;
            }
        }

        long range = latestEnd.getTime() - earliestStart.getTime() + DAY_OFFSET;
        long[] offsets = new long[courses.length];
        long[] widths = new long[courses.length];
        String[] conflicts = new String[courses.length];
        boolean conflictDetected = false;

        for(int i = 0; i < courses.length; i++) {
            Date courseStart = courses[i].getCourseStartDate();
            Date courseEnd = courses[i].getCourseEndDate();
            offsets[i] = (courseStart.getTime() - earliestStart.getTime()) * 100L / range;
            widths[i] = (courseEnd.getTime() - courseStart.getTime() + DAY_OFFSET) * 100L / range;
            //if (EC.start <= SC.end && EC.end >= SC.start)
            if( (enrollCourse[0].getCourseId() != courses[i].getCourseId())
                    && (enrollCourse[0].getCourseEndDate().compareTo(courseStart) >= 0)
                    && (enrollCourse[0].getCourseStartDate().compareTo(courseEnd) <= 0) ) {
                conflicts[i] = "conflict";
                conflictDetected = true;
            } else {
                conflicts[i] = "noConflict";
            }
        }
        request.setAttribute("enrollCourse", enrollCourse[0]);
        request.setAttribute("courses", courses);
        request.setAttribute("offsets", offsets);
        request.setAttribute("widths", widths);
        request.setAttribute("conflicts", conflicts);
        request.setAttribute("enrollments", enrollments);
        return conflictDetected;
    }
}
