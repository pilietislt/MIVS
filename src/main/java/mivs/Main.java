package mivs;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("MIVS");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserName:");
        String userName = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();
        new Login().login(userName, password);
/*
        User user = new User("123", "456");
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put("123", user);

        // irasymas i faila

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("users"));
            outputStream.writeObject(userHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //nuskaitymas is failo
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("users"));
            HashMap<String, User> readUser = (HashMap<String, User>) inputStream.readObject();
            System.out.println( readUser.get("123").getPassword());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

*/

    }

}
