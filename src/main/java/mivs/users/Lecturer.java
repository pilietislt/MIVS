package mivs.users;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lecturer extends User {

    private String lecturerCode;
    private int personalNumber;
    private LocalDate dateOfBirth;
    private String  email;
    private int mobileNumber;
    private Gender gender;
    private String address;
    private ArrayList<String> runningCourses = new ArrayList<String>();

    public Lecturer(String username, String password, Role role, String firstName, String secondName, String lecturerCode) {
        super(username, password, role, firstName, secondName);
        this.lecturerCode = lecturerCode;
    }

    public ArrayList<String> getRunningCourses() {
        return runningCourses;
    }

    public void setRunningCourses(ArrayList<String> runningCourses) {
        this.runningCourses = runningCourses;
    }

    public String getLecturerCode() {
        return lecturerCode;
    }

    public void setLecturerCode(String lecturerCode) {
        this.lecturerCode = lecturerCode;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
