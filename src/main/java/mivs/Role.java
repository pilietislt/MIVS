package mivs;

public enum Role {

    admin ("Admin"),
    lecturer ("Lecturer"),
    student ("Student");

    private final String role;

    Role(String s) {
        this.role = s;
    }
    public String get(){
        return role;
    }

}
