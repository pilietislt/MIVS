package mivs.UI;

import mivs.back_end.Services;
import mivs.courses.Course;
import mivs.users.Admin;
import mivs.users.User;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class AdminUI {

    public void Menu(String username) {

        HashMap<String, Admin> activeUser = null;

        try {
            activeUser = (HashMap<String, Admin> ) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello " +  activeUser.get(username).getRole()+ " "+ activeUser.get(username).getFirstName());
        System.out.println("1. Add User");
        System.out.println("2. User List");
        System.out.println("3. Add Course");
        System.out.println("4. Course List");
        System.out.println("5. Logout");
        int choice = ScannerUtils.scanInt();

        switch (choice) {
            case 1:
                new Services().addUser();
                Menu(username);
                break;

            case 2:
                printUserList();
                Menu(username);
                break;
            case 3:
                new Services().addCourse();
                Menu(username);
                break;
            case 4:
                new Services().courseList();
                Menu(username);
                break;
            default:
                break;
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




}