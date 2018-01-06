package mivs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {

    public void adminMeniu() {
        System.out.println("1. Add User");
        System.out.println("2. User List");
        System.out.println("3.");
        System.out.println("4.exit");
        Scanner scanner = new Scanner(System.in);
        int chooice = scanner.nextInt();

        switch (chooice) {
            case 1:
                new AddUser().add();
                adminMeniu();

                break;

            case 2:
                printUserList();
                adminMeniu();
                break;
            default:

                break;
        }
    }



    public void printUserList(){
        //nuskaitymas is failo
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



        int nr = 0;

        System.out.printf("%-3s %-10s %-10s %-10s %-10s\n", "Nr.","Username","Role","First name","Second Name" );
        for(Map.Entry<String, User> entry : readUser.entrySet()) {
            //String key = entry.getKey();
            User value = entry.getValue();
            nr +=1;

            System.out.printf("%-3s %-10s %-10s %-10s %-10s\n", nr,value.getUsername(),value.getRole(), value.getFirstName(), value.getSecondName() );




        }


    }


}
