package mivs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class AddUser {
    public void add() {
        Role roles = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserName:");
        String userName = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();
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
                break;
            case 3:
                roles = Role.STUDENT;
                break;
            default:
                System.out.println("wrong input");

                break;
        }
        User user = new User(userName, password,  roles);
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put(userName, user);

        // irasymas i faila

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("users"));
            outputStream.writeObject(userHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAdmin(){
        User user = new User("admin", "admin",  Role.ADMIN);
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

}
