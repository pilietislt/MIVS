package mivs.users;

public class Lecturer extends User {

    private String lecturerCode;

    public Lecturer(String username, String password, Role role, String firstName, String secondName, String lecturerCode) {
        super(username, password, role, firstName, secondName);
        this.lecturerCode = lecturerCode;
    }

    public String getLecturerCode() {
        return lecturerCode;
    }

    public void setLecturerCode(String lecturerCode) {
        this.lecturerCode = lecturerCode;
    }
}
