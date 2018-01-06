package mivs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

    public boolean secondLogin(String userName, String password) {

        //nuskaitymas is failo
        ObjectInputStream inputStream = null;
        HashMap<String, User> readUser = new HashMap<String, User>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream("users"));
            readUser = (HashMap<String, User>) inputStream.readObject();
            readUser.get(userName).getPassword();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Wrong User Name or Password!!");
            return false;
        }

        if (readUser.get(userName).getPassword().equals(password)) {
            System.out.println("OK");
            new Admin().adminMeniu();

            return true;

        } else {
            System.out.println("Wrong User Name or Password!!");
            return false;
        }


    }

    public boolean firsLogin(String userName, String password) {
        final String myUserName = "admin";
        final String myPassword = "admin";

        if (userName.equalsIgnoreCase(myUserName) && password.equals(myPassword)) {
            new AddUser().addAdmin();
            new Admin().adminMeniu();
            return true;
        }
        System.out.println("Wrong User Name or Password!!");
        return false;
    }

    public void login() {
        System.out.println("MIVS");


        File f = new File("users");
        if (f.exists() && !f.isDirectory()) {
            secondLogin(loginUser(), loginPassword());
        } else {
            System.out.println("__First Login__");
            firsLogin(loginUser(), loginPassword());
        }
    }

    public String loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserName:");
        String username = scanner.next();
        return username;
    }

    public String loginPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserName:");
        String password = scanner.next();
        return password;
    }


}
