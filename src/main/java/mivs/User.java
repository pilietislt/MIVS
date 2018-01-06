package mivs;

import java.io.Serializable;

public class User implements Serializable{

    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String secondName;
    private String code;

    public User(String username, String password, Role role, String firstName, String secondName, String code) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.secondName = secondName;
        this.code = code;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public String getCode(){
        return code;
    }
}
