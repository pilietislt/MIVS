package mivs.services;

import mivs.users.Gender;
import mivs.users.User;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class UserServices {

    public Gender selectGender() {

        //Gender gender = null;
        for (Gender g : Gender.values()) {
            System.out.println(g.get() + ". " + g);
        }
        int choice = ScannerUtils.scanInt("Select Gender:");
        switch (choice) {
            case 1:
                return Gender.FEMALE;

            case 2:
                return Gender.MALE;

            default:
                System.out.println("wrong input");
                return null;


        }
    }

    public void printUserList() {

        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-10s %-10s\n", "Nr.", "Username", "Role", "First name", "Second Name");
            for (Map.Entry<String, User> entry : readUser.entrySet()) {
                //String key = entry.getKey();
                User value = entry.getValue();
                nr += 1;
                System.out.printf("%-3s %-10s %-10s %-10s %-10s\n", nr, value.getUsername(), value.getRole(), value.getFirstName(), value.getSecondName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public User readUser(String username) {
        HashMap<String, User> readUser = null;

        try {
            readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return readUser.get(username);
    }
}
