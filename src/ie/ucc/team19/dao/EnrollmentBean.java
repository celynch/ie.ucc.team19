package ie.ucc.team19.dao;

import java.sql.Timestamp;

/**
 * Bean to relate a student to a course for enrollment. 
 * @author Cormac
 */
public class EnrollmentBean {
    private int courseId;
    private int studentId;
    private boolean pending;
    private boolean paidDeposit;
    private boolean paidFee;
    private boolean issuedRefund;
    private Timestamp enrollDate;

    public EnrollmentBean() {

    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public boolean isPending() {
        return pending;
    }
    public void setPending(boolean pending) {
        this.pending = pending;
    }
    public boolean isPaidDeposit() {
        return paidDeposit;
    }
    public void setPaidDeposit(boolean paidDeposit) {
        this.paidDeposit = paidDeposit;
    }
    public boolean isPaidFee() {
        return paidFee;
    }
    public void setPaidFee(boolean paidFee) {
        this.paidFee = paidFee;
    }
    public boolean isIssuedRefund() {
        return issuedRefund;
    }
    public void setIssuedRefund(boolean issuedRefund) {
        this.issuedRefund = issuedRefund;
    }
    public Timestamp getEnrollDate() {
        return enrollDate;
    }
    public void setEnrollDate(Timestamp enrollDate) {
        this.enrollDate = enrollDate;
    }
}
