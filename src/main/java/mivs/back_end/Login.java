package mivs.back_end;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import mivs.users.User;
import mivs.users.Role;
import mivs.utils.ScannerUtils;
import mivs.UI.*;

public class Login {

    public boolean secondLogin(String userName, String password) {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mivs", "java", "java");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select user_password from user where user_username='"+userName+"'");
            while (rs.next()) {
                if (rs.getString(1).equals(password)){
                   return true;
                }

                  //  System.out.println(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

//
//
//
//
//        //nuskaitymas is failo
//        ObjectInputStream inputStream = null;
//        HashMap<String, User> readUser = new HashMap<String, User>();
//        try {
//            inputStream = new ObjectInputStream(new FileInputStream("files/users"));
//            readUser = (HashMap<String, User>) inputStream.readObject();
//            readUser.get(userName).getPassword();
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//
//        } catch (InvalidClassException e) {
//            System.out.println("error");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            //e.printStackTrace();
//            System.out.println("Wrong User Name or Password!!");
//            return false;
//        }
//
//        if (readUser.get(userName).getPassword().equals(password)) {
//            System.out.println("OK");
//            if (readUser.get(userName).getRole().equals(Role.STUDENT)) {
//                new StudentUI().Menu(userName);
//
//            } else if (readUser.get(userName).getRole().equals(Role.LECTURER)) {
//                new LecturerUI().Menu(userName);
//
//            } else {
//                new AdminUI().Menu(userName);
//
//            }
//
//
//            return true;
//
//        } else {
//            System.out.println("Wrong User Name or Password!!");
//            return false;
//        }

        return false;
    }

    public boolean firsLogin(String userName, String password) {
        final String myUserName = "admin";
        final String myPassword = "admin";

        if (userName.equalsIgnoreCase(myUserName) && password.equals(myPassword)) {
            new Services().addFirstAdmin();
            new AdminUI().Menu(userName);
            return true;
        }
        System.out.println("Wrong User Name or Password!!");
        return false;
    }

    public boolean firsLoginFX(String userName, String password) {
        final String myUserName = "admin";
        final String myPassword = "admin";

        if (userName.equalsIgnoreCase(myUserName) && password.equals(myPassword)) {

           // new AdminUI().Menu(userName);
            return true;
        }
       // System.out.println("Wrong User Name or Password!!");
        return false;
    }

    public void login() {
        System.out.println("MIVS");


        File f = new File("files/users");
        if (f.exists() && !f.isDirectory()) {
            secondLogin(loginUser(), loginPassword());
        } else {
            System.out.println("__First Login__");
            firsLogin(loginUser(), loginPassword());
        }
    }

    public String loginUser() {
        return ScannerUtils.scanString("Enter UserName:");
    }

    public String loginPassword() {
        return ScannerUtils.scanString("Enter Password:");
    }


}
