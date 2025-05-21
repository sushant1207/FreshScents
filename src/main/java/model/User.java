package model;

import java.sql.Date;

public class User {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String role;
    private Date dob;
    private String gender;
    private String profileImage;
    private String operators;

   
    public User() {}

    public User(String name, String email, String password, String role, Date dob, String gender, String profileImage, String operators) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dob = dob;
        this.gender = gender;
        this.profileImage = profileImage;
        this.operators = operators;
    }

    // Getters & Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getoperators() {
    	return operators;
    }
    
    public void setOperators(String operators) {
    	this.operators= operators;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
