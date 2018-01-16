package mivs.UI;

import mivs.back_end.Services;
import mivs.services.AdminServices;
import mivs.services.UserServices;
import mivs.users.Admin;

import mivs.utils.ScannerUtils;


public class AdminUI {

    public void Menu(String username) {

        Admin admin = (Admin) new UserServices().readUser(username);

        System.out.println("Hello " +  admin.getRole()+ " "+ admin.getFirstName());
        System.out.println("===================");
        System.out.println("    Admin menu");
        System.out.println("===================");
        System.out.println("1. Add User");
        System.out.println("2. User List");
        System.out.println("3. Add Course");
        System.out.println("4. Course List");
        System.out.println("5. Logout");

        switch (ScannerUtils.scanInt()) {
            case 1:
                new AdminServices().addUser();
                Menu(username);
                break;
            case 2:
                new UserServices().printUserList();
                Menu(username);
                break;
            case 3:
                new AdminServices().addCourse();
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






}