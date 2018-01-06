package mivs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Login {

    public boolean login(String userName, String password) {


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
        }


        final String myUserName = "admin";
        final String myPassWord = "admin";

        if (readUser.get(userName).getPassword().equals(password)) {
            System.out.println("OK");
            return true;



        }else {
            System.out.println("Error");
            return false;
        }
    }






}
