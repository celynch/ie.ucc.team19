package ie.ucc.team19.dao;

import java.sql.Date;

public class CourseBean {
    private int course_id;
    private String course_title;
    private double fee;
    private int spaces;
    private String course_category;
    private String content;
    private Date enroll_start_date;
    private Date enroll_end_date;
    private String url_timetable;

    public int getCourse_id() {
        return course_id;
    }
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
    public String getCourse_title() {
        return course_title;
    }
    public void setCourse_title(String course_title) {
        this.course_title = course_title;
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
    public String getCourse_category() {
        return course_category;
    }
    public void setCourse_category(String course_category) {
        this.course_category = course_category;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getEnroll_start_date() {
        return enroll_start_date;
    }
    public void setEnroll_start_date(Date enroll_start_date) {
        this.enroll_start_date = enroll_start_date;
    }
    public Date getEnroll_end_date() {
        return enroll_end_date;
    }
    public void setEnroll_end_date(Date enroll_end_date) {
        this.enroll_end_date = enroll_end_date;
    }
    public String getUrl_timetable() {
        return url_timetable;
    }
    public void setUrl_timetable(String url_timetable) {
        this.url_timetable = url_timetable;
    }

    
}
