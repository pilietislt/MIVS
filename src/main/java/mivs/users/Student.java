package mivs.users;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends User{
    private String studentCode;
    private int personalNumber;
    private LocalDate dateOfBirth;
    private String  email;
    private int mobileNumber;
    private Gender gender;
    private String address;
    private ArrayList<String> runningCourses;

    public Student(String username, String password, Role role, String firstName, String secondName, String studentCode) {
        super(username, password, role, firstName, secondName);
        this.studentCode = studentCode;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
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

    public ArrayList<String> getRunningCourses() {
        return runningCourses;
    }

    public void setRunningCourses(ArrayList<String> runningCourses) {
        this.runningCourses = runningCourses;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }
}
