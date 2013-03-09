package ie.ucc.team19.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class StudentBean {
    private String first_name;
    private String last_name;
    private String email;
    private String password_hash;
    private String address_line1;
    private String address_line2;
    private String address_line3;
    private String country;
    private String telephone; 
    private Date date_of_birth;
    private char gender;
    private boolean authenticated;
    private String auth_string;
    private Timestamp date_ac_created;
    private String cookie_token;

    public StudentBean() {

    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword_hash() {
        return password_hash;
    }
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    public String getAddress_line1() {
        return address_line1;
    }
    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }
    public String getAddress_line2() {
        return address_line2;
    }
    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }
    public String getAddress_line3() {
        return address_line3;
    }
    public void setAddress_line3(String address_line3) {
        this.address_line3 = address_line3;
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
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    public String getAuth_string() {
        return auth_string;
    }
    public void setAuth_string(String auth_string) {
        this.auth_string = auth_string;
    }
    public Timestamp getDate_ac_created() {
        return date_ac_created;
    }
    public void setDate_ac_created(Timestamp date_ac_created) {
        this.date_ac_created = date_ac_created;
    }
    public String getCookie_token() {
        return cookie_token;
    }
    public void setCookie_token(String cookie_token) {
        this.cookie_token = cookie_token;
    }
}
