package ie.ucc.team19.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class StudentBean {
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String country;
    private String telephone;
    private Date dateOfBirth;
    private char gender;
    private boolean authenticated;
    private String authString;
    private Timestamp dateRegistered;
    private String cookieToken;
    private boolean emailOptIn;

    public StudentBean() {

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
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    public String getAuthString() {
        return authString;
    }
    public void setAuthString(String authString) {
        this.authString = authString;
    }
    public Timestamp getDateRegistered() {
        return dateRegistered;
    }
    public void setDateRegistered(Timestamp dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
    public String getCookieToken() {
        return cookieToken;
    }
    public void setCookieToken(String cookieToken) {
        this.cookieToken = cookieToken;
    }
    public boolean getEmailOptIn() {
        return emailOptIn;
    }
    public void setEmailOptIn(boolean emailOptIn) {
        this.emailOptIn = emailOptIn;
    }
}
