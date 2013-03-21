package ie.ucc.team19.dao;

import java.sql.Date;

public class CourseBean {
    private int courseId;
    private String courseTitle;
    private double fee;
    private int spaces;
    private String courseCategory;
    private String content;
    private Date enrollStartDate;
    private Date enrollEndDate;
    private Date courseStartDate;
    private Date courseEndDate;

    public CourseBean() {

    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
    public int getSpaces() {
        return spaces;
    }
    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }
    public String getCourseCategory() {
        return courseCategory;
    }
    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getEnrollStartDate() {
        return enrollStartDate;
    }
    public void setEnrollStartDate(Date enrollStartDate) {
        this.enrollStartDate = enrollStartDate;
    }
    public Date getEnrollEndDate() {
        return enrollEndDate;
    }
    public void setEnrollEndDate(Date enrollEndDate) {
        this.enrollEndDate = enrollEndDate;
    }
    public Date getCourseStartDate() {
        return courseStartDate;
    }
    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }
    public Date getCourseEndDate() {
        return courseEndDate;
    }
    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }
}
