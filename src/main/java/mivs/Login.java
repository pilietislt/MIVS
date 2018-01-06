package mivs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

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
        } catch (NullPointerException e){
            //e.printStackTrace();
            System.out.println("Wrong User Name or Password!!");
            return  false;
        }

        if (readUser.get(userName).getPassword().equals(password)) {
            System.out.println("OK");
            return true;

        }else {
            System.out.println("Wrong User Name or Password!!");
            return false;
        }



    }

    public boolean firsLogin(String userName, String password){
        final String myUserName = "admin";
        final String myPassword = "admin";
        System.out.println("__First Login__");
        if (userName.equalsIgnoreCase(myUserName)&& password.equals(myPassword)){
            new AddUser().addAdmin();
            return true;
        }
        System.out.println("Wrong User Name or Password!!");
        return false;
    }


    public boolean login (String userName, String password){
        File f = new File("users");
        if(f.exists() && !f.isDirectory()) {
           return secondLogin(userName,password);
        }
        else {
            return firsLogin(userName,password);
        }
    }






}
