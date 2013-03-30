package ie.ucc.team19.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ie.ucc.team19.dao.CourseBean;
import ie.ucc.team19.dao.DBConnectionManager;
import ie.ucc.team19.dao.EnrollmentBean;
import ie.ucc.team19.dao.StudentBean;

/**
 * Class for student enrollment. Handles new and updated enrollments.
 * @author Cormac
 */
public class EnrollStudent {
    private DBConnectionManager connector;
    private static final long DAY_OFFSET = 1000 *60 * 60 * 24;
    private Date earliestStart;
    private Date latestEnd;
    private CourseBean[] courses;
    private CourseBean[] enrollCourse;
    private long[] offsets;
    private long[] widths;
    private String[] conflicts;
    private boolean conflictDetected = false;
    private boolean alreadyEnrolled = false;

    /**
     * Contructor for enrollStudent. Utilises injected db connector.
     * @param connector - the DBConnectionManager to use.
     */
    public EnrollStudent(DBConnectionManager connector) {
        this.connector = connector;
    }

    /**
     * Enter new enrollment to db. Incomplete enrollment deleted after 30 mins.
     * @param courseId - the courseId enrolled to.
     * @param studentId - the studentId enrolling.
     * @param pending - boolean true till initial transaction complete.
     * @param paidDeposit - boolean has deposit been paid
     * @param paidFee - boolean has fee been paid 
     * @param issuedRefund - boolean has refund been paid 
     * @param enrollDate - timestamp for enrollment
     */
    public void enrollToCourse(String courseId, String studentId, boolean pending,
            boolean paidDeposit, boolean paidFee, boolean issuedRefund, String enrollDate) {
        String query = "INSERT INTO enrollments "
            + "(courseId, studentId, pending, paidDeposit, paidFee, issuedRefund, enrollDate) "
            + "VALUES (?,?,?,?,?,?,?)";
        Object[] params = {courseId,studentId,1,0,0,0,enrollDate};
        connector.Insert(query, params);
    }

    /**
     * Deletes enrollment from db by specified courseId, studentId composite key
     * @param courseId - the course enrolled in.
     * @param studentId - the student enrolled
     */
    public void unEnrollFromCourse(String courseId, String studentId) {
        String query = "DELETE FROM enrollments WHERE courseId = ? AND studentId = ?";
        Object[] params = {courseId,studentId};
        connector.Insert(query, params);
    }

    /**
     * Fetches beans to reprent each enrollment of the specified student.
     * @param studentId - the enrolled student
     * @return - the enrollments involving the student
     */
    private EnrollmentBean[] getEnrollmentsByStudentId(String studentId) {
        String query = "SELECT * "
                     + "FROM enrollments "
                     + "WHERE studentId = ?";
        Object[] params = {studentId};
        ArrayList<Map<String,String[]>> resultTable = connector.Select(query, params);
        EnrollmentBean[] enrollments = new EnrollmentBean[resultTable.size()];
        enrollments = (EnrollmentBean[]) new FetchBeanUtils(connector).sqlBeanPopulate(enrollments, resultTable);
        return enrollments;
    }

    /**
     * Fetches beans to reprent each course the specified student is
     * enrolled in.
     * @param studentId - the enrolled student
     * @return - the courses the student is rolled in.
     */
    public CourseBean[] getCoursesByStudentId(String studentId) {
        String query = "SELECT * " +
        		       "FROM courses " +
        		       "WHERE courseId IN (" +
        		           "SELECT courseId " +
        		           "FROM enrollments " +
        		           "WHERE studentId = ?) " +
	          		  "ORDER BY courseStartDate";
        Object[] params = {studentId};
        ArrayList<Map<String,String[]>> resultTable = connector.Select(query, params);
        CourseBean[] courses = new CourseBean[resultTable.size()];
        courses = (CourseBean[]) new FetchBeanUtils(connector).sqlBeanPopulate(courses, resultTable);
        return courses;
    }

    /**
     * Uses the request to get the student and requested course to enroll in.
     * Gets students existing enrollments and compares new course for time
     * conflicts. Gets range of all dates, gets proportion of course length
     * and start date relative to range for each course, for schedule layout.
     * Data for view attached in request scope
     * @param request - the request for passing enrollment info in and schedule info out. 
     */
    public void detectConflicts(HttpServletRequest request) {
        // setup DAOs
        FetchBeanUtils fetcher = new FetchBeanUtils(connector);

        // get params from request & session
        StudentBean student = (StudentBean) request.getSession().getAttribute("user");
        String studentId = student.getStudentId();
        String enrollCourseId = request.getParameter("enrollCourseId");
        System.out.println("course selected: " + enrollCourseId);

        // get data from db
        enrollCourse = fetcher.getCourseByCourseId(enrollCourseId);
        courses = getCoursesByStudentId(studentId);
        EnrollmentBean[] enrollments = getEnrollmentsByStudentId(studentId);

        // algo for detecting & displaying date conflicts
        setStartEndDates();
        long range = latestEnd.getTime() - earliestStart.getTime() + DAY_OFFSET;
        offsets = new long[courses.length];
        widths = new long[courses.length];
        conflicts = new String[courses.length];

        setStartEndDates();
        scheduleLayout(range);
        markConflicts();

        request.setAttribute("enrollCourse", enrollCourse[0]);
        request.setAttribute("courses", courses);
        request.setAttribute("offsets", offsets);
        request.setAttribute("widths", widths);
        request.setAttribute("conflicts", conflicts);
        request.setAttribute("enrollments", enrollments);
        request.setAttribute("alreadyEnrolled", alreadyEnrolled);
        request.setAttribute("conflictDetected", conflictDetected);
        if(!conflictDetected && !alreadyEnrolled ) {
            String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            enrollToCourse(enrollCourseId, studentId, true, false, false, false, dateNow);
        }
    }

    /**
     * Determines which date is earliest start date and latest end date.
     */
    private void setStartEndDates() {
        earliestStart = enrollCourse[0].getCourseStartDate();
        latestEnd = enrollCourse[0].getCourseEndDate();
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
    }

    /**
     * Determines course location along a range for each course. Sets
     * layout info for schedule.
     * @param range
     */
    private void scheduleLayout(long range) {
        for(int i = 0; i < courses.length; i++) {
            Date courseStart = courses[i].getCourseStartDate();
            Date courseEnd = courses[i].getCourseEndDate();
            offsets[i] = (courseStart.getTime() - earliestStart.getTime()) * 100L / range;
            widths[i] = (courseEnd.getTime() - courseStart.getTime() + DAY_OFFSET) * 100L / range;
        }
    }

    /**
     * For highlighting courses which conflict on schedule, overlapping
     * course elements will have class="conflict". Boolean conflictDetected
     * used to remove payment buttons in view
     */
    private void markConflicts() {
        for(int i = 0; i < courses.length; i++) {
            Date courseStart = courses[i].getCourseStartDate();
            Date courseEnd = courses[i].getCourseEndDate();
            if( enrollCourse[0].getCourseEndDate().compareTo(courseStart) >= 0 &&
                    enrollCourse[0].getCourseStartDate().compareTo(courseEnd) <= 0 ) {
                conflicts[i] = "conflict";
                conflictDetected = true;
                if(enrollCourse[0].getCourseId() != courses[i].getCourseId()) {
                    alreadyEnrolled = true;
                }
            } else {
                conflicts[i] = "noConflict";
            }
        }
    }
}
