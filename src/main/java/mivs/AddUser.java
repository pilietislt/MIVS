package mivs;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class AddUser {
    public void add() {
        Role roles = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserName:");
        String userName = scanner.next();
        userName = checkUnique(userName);
        System.out.println("Enter password");
        String password = scanner.next();
        System.out.println("Enter First Name:");
        String firstName = scanner.next();
        System.out.println("Enter SecondName");
        String secondName = scanner.next();
        System.out.println("Choose role");
        for (Role role : Role.values()) {
            System.out.println(role.get() + ". " + role);
        }
        int role = scanner.nextInt();
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
        //System.out.println(genereteCode(firstName,secondName,roles));

        ObjectInputStream inputStream = null;
        HashMap<String, User> readUser = new HashMap<String, User>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream("users"));
            readUser = (HashMap<String, User>) inputStream.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


        User user = new User(userName, password, roles, firstName, secondName,genereteCode(firstName,secondName,roles));
        readUser.put(userName, user);

        // irasymas i failaadmin
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("users"));
            outputStream.writeObject(readUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void addAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String firstName = scanner.next();
        System.out.println("Enter SecondName");
        String secondName = scanner.next();
        User user = new User("admin", "admin", Role.ADMIN, firstName, secondName,genereteCode(firstName,secondName,Role.ADMIN));
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put("admin", user);

        // irasymas i faila

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("users"));
            outputStream.writeObject(userHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String checkUnique(String userName) {
        try {
            readFile().get(userName).getUsername();
        } catch (NullPointerException e) {
            return userName;
        }

        System.out.println("User alredy exist");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserName:");
        userName = scanner.next();
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



    private HashMap<String, User> readFile() {
        ObjectInputStream inputStream = null;
        HashMap<String, User> readUser = new HashMap<String, User>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream("files/users"));
            readUser = (HashMap<String, User>) inputStream.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readUser;

    }


}
