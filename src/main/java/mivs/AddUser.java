package mivs;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import mivs.services.*;

public class AddUser {
    public void add() {
        Role roles = null;
        System.out.println("Enter UserName:");
        String userName = ScannerService.scanString();
        userName = checkUnique(userName);
        System.out.println("Enter password");
        String password = ScannerService.scanString();
        System.out.println("Enter First Name:");
        String firstName = ScannerService.scanString();
        System.out.println("Enter SecondName");
        String secondName = ScannerService.scanString();
        System.out.println("Choose role");
        for (Role role : Role.values()) {
            System.out.println(role.get() + ". " + role);
        }
        int role = ScannerService.scanInt();
        switch (role) {
            case 1:
                roles = Role.ADMIN;
                break;
            case 2:
                roles = Role.LECTURER;
                genereteCode(firstName,secondName,roles);

                break;
            case 3:
                roles = Role.STUDENT;
                break;
            default:
                System.out.println("wrong input");

                break;
        }

        try {
            HashMap<String, User> readUser = (HashMap<String, User> ) IOService.readObjectFromFile("files/users");
            User user = new User(userName, password, roles, firstName, secondName,genereteCode(firstName,secondName,roles));
            readUser.put(userName, user);
            IOService.writeObjectToFile(readUser,"files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addAdmin() {

        System.out.println("Enter First Name:");
        String firstName = ScannerService.scanString();
        System.out.println("Enter SecondName");
        String secondName = ScannerService.scanString();
        User user = new User("admin", "admin", Role.ADMIN, firstName, secondName,genereteCode(firstName,secondName,Role.ADMIN));
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put("admin", user);
        IOService.writeObjectToFile(userHashMap,"files/users");
    }

    private String checkUnique(String userName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User> ) IOService.readObjectFromFile("files/users");
            readUser.get(userName).getUsername();
        } catch (NullPointerException e) {
            return userName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("User alredy exist");
        System.out.println("Enter UserName:");
        userName = ScannerService.scanString();
        checkUnique(userName);
        return userName;
    }

    private String genereteCode(String firstName, String secondName, Role role){
        Random generator = new Random();
        Integer rand = generator.nextInt(999);
        Integer i =role.get();
        Character a = firstName.toUpperCase().charAt(0);
        Character b = secondName.toUpperCase().charAt(0);
        String code =  i.toString()+ a.toString()+b.toString()+rand.toString();
        return code;
    }





}
