package mivs;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import mivs.services.*;


public class Admin {

    public void adminMenu() {
        System.out.println("1. Add User");
        System.out.println("2. User List");
        System.out.println("3. Add Course");
        System.out.println("4. Course List");
        System.out.println("5. Exit");
       int choice = ScannerService.scanInt();

        switch (choice) {
            case 1:
                new AddUser().add();
                adminMenu();
                break;

            case 2:
                printUserList();
                adminMenu();
                break;
            case 3:
               new Course().addCourse();
                adminMenu();
                break;
            case 4:
                new Course().courseList();
                adminMenu();
                break;
            default:
                break;
        }
    }



    public void printUserList(){

        try {
            HashMap<String, User> readUser = ( HashMap<String, User>)IOService.readObjectFromFile("files/users");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-10s %-10s\n", "Nr.","Username","Role","First name","Second Name" );
            for(Map.Entry<String, User> entry : readUser.entrySet()) {
                //String key = entry.getKey();
                User value = entry.getValue();
                nr +=1;
                System.out.printf("%-3s %-10s %-10s %-10s %-10s\n", nr,value.getUsername(),value.getRole(), value.getFirstName(), value.getSecondName() );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
