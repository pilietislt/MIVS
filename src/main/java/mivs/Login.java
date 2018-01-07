package mivs;

import java.io.*;
import java.util.HashMap;
import mivs.services.ScannerService;
public class Login  {

    public boolean secondLogin(String userName, String password) {

        //nuskaitymas is failo
        ObjectInputStream inputStream = null;
        HashMap<String, User> readUser = new HashMap<String, User>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream("files/users"));
            readUser = (HashMap<String, User>) inputStream.readObject();
            readUser.get(userName).getPassword();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (InvalidClassException e) {
            System.out.println("error");
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
            if (readUser.get(userName).getRole().equals(Role.STUDENT)) {
                new Student(readUser.get(userName).getUsername(),
                        readUser.get(userName).getPassword(),
                        readUser.get(userName).getRole(),
                        readUser.get(userName).getFirstName(),
                        readUser.get(userName).getSecondName(),
                        readUser.get(userName).getCode()).studentMeniu();
            }else if (readUser.get(userName).getRole().equals(Role.LECTURER)) {
                new Lecturer(readUser.get(userName).getUsername(),
                        readUser.get(userName).getPassword(),
                        readUser.get(userName).getRole(),
                        readUser.get(userName).getFirstName(),
                        readUser.get(userName).getSecondName(),
                        readUser.get(userName).getCode()).lecturerMeniu();
            }else {
                new Admin().adminMenu();
            }


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
            new Admin().adminMenu();
            return true;
        }
        System.out.println("Wrong User Name or Password!!");
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
        System.out.println("Enter UserName:");
        return ScannerService.scanString();
    }

    public String loginPassword() {
        System.out.println("Enter Password:");
        return ScannerService.scanString();
    }


}
