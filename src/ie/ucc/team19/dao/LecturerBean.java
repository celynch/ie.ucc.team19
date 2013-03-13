package ie.ucc.team19.dao;

public class LecturerBean {
    private int lecturer_id;
    private String firstName;
    private String lastName;
    private String email;
    private String lecturertitle;
    private String position;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String country;
    private String telephone;

    public LecturerBean() {
        
    }

    public int getLecturer_id() {
        return lecturer_id;
    }
    public void setLecturer_id(int lecturer_id) {
        this.lecturer_id = lecturer_id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLecturertitle() {
        return lecturertitle;
    }
    public void setLecturertitle(String lecturertitle) {
        this.lecturertitle = lecturertitle;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine3() {
        return addressLine3;
    }
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
