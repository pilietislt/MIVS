package mivs.back_end;

import java.io.*;
import java.util.HashMap;
import java.util.Random;

import mivs.users.*;
import mivs.users.Role;
import mivs.utils.*;

public class Services {
    public void addUser() {
        Role roles = null;
        System.out.println("Enter UserName:");
        String userName = ScannerUtils.scanString();
        userName = checkUnique(userName);
        System.out.println("Enter password");
        String password = ScannerUtils.scanString();
        System.out.println("Enter First Name:");
        String firstName = ScannerUtils.scanString();
        System.out.println("Enter SecondName");
        String secondName = ScannerUtils.scanString();
        System.out.println("Choose role");
        for (Role role : Role.values()) {
            System.out.println(role.get() + ". " + role);
        }
        int role = ScannerUtils.scanInt();
        switch (role) {
            case 1:
                addAdmin(userName, password, firstName, secondName);
                break;
            case 2:
                addLecturer(userName, password, firstName, secondName);

                break;
            case 3:
                addStudent(userName, password, firstName, secondName);
                break;
            default:
                System.out.println("wrong input");

                break;
        }

//        try {
//            HashMap<String, User> readUser = (HashMap<String, User> ) IOUtils.readObjectFromFile("files/users");
//            User user = new Student(userName, password, roles, firstName, secondName,genereteCode(firstName,secondName,roles));
//            readUser.put(userName, user);
//            IOUtils.writeObjectToFile(readUser,"files/users");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }


    public void addFirstAdmin() {

        System.out.println("Enter First Name:");
        String firstName = ScannerUtils.scanString();
        System.out.println("Enter SecondName");
        String secondName = ScannerUtils.scanString();
        User user = new Admin("admin", "admin", Role.ADMIN, firstName, secondName);
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put("admin", user);
        IOUtils.writeObjectToFile(userHashMap, "files/users");
    }

    private String checkUnique(String userName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            readUser.get(userName).getUsername();
        } catch (NullPointerException e) {
            return userName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("User alredy exist");
        System.out.println("Enter UserName:");
        userName = ScannerUtils.scanString();
        checkUnique(userName);
        return userName;
    }

    private String genereteCode(String firstName, String secondName, Role role) {
        Random generator = new Random();
        Integer rand = generator.nextInt(999);
        Integer i = role.get();
        Character a = firstName.toUpperCase().charAt(0);
        Character b = secondName.toUpperCase().charAt(0);
        String code = i.toString() + a.toString() + b.toString() + rand.toString();
        return code;
    }

    private void addStudent(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Student(userName, password, Role.STUDENT, firstName, secondName, genereteCode(firstName, secondName, Role.STUDENT));
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addLecturer(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Lecturer(userName, password, Role.LECTURER, firstName, secondName, genereteCode(firstName, secondName, Role.LECTURER));
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addAdmin(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Admin(userName, password, Role.ADMIN, firstName, secondName);
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
