package ie.ucc.team19.dao;

/**
 * Bean to represent a comment submitted to the db. 
 * @author Cormac
 */
public class CommentBean {
    private int commentId;
    private int studentId;
    private String email;
    private String firstName;
    private String subject;
    private String messageText;
    private boolean reviewed;
    
    public CommentBean() {
        
    }
    public int getCommentId() {
        return commentId;
    }
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public boolean isReviewed() {
        return reviewed;
    }
    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

}
