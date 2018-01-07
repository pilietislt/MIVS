package mivs;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main implements Serializable{
    public static void main(String[] args)  {




        while (true){

         new Login().login();
        }
//
//        ObjectInputStream inputStream = null;
//        try {
//            inputStream = new ObjectInputStream(new FileInputStream("users"));
//            HashMap<String, User> readUser = (HashMap<String, User>) inputStream.readObject();
//            System.out.println( readUser.get("admin").getPassword());
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





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
