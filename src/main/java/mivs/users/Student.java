package mivs.users;

public class Student extends User{
    private String studentCode;

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
}
