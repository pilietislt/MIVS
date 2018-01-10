package mivs.users;

public enum Role {

    ADMIN (1),
    LECTURER (2),
    STUDENT  (3);

    private final int role;

    Role(int s) {
        this.role = s;
    }
    public int get(){
        return role;
    }

}
