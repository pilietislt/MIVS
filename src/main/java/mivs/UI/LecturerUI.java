package mivs.UI;


import mivs.services.UserServices;
import mivs.users.*;
import mivs.utils.ScannerUtils;
import mivs.services.LecturerServices;



public class LecturerUI {

    public void Menu (String username){

        Lecturer lecturer = (Lecturer) new UserServices().readUser(username);
        System.out.println("Hello " +  lecturer.getRole()+ " "+ lecturer.getFirstName()+ " "+ lecturer.getLecturerCode());
        System.out.println("===================");
        System.out.println("   Lecturer menu");
        System.out.println("===================");
        System.out.println("1. View info");
        System.out.println("2. Edit info");
        System.out.println("3. View Your Own Courses");
        System.out.println("4. Exit");


        switch (ScannerUtils.scanInt()) {
            case 1:
                new LecturerServices().view(username);
                Menu(username);
                break;
            case 2:
                new LecturerServices().edit(username);
                Menu(username);
                break;
            case 3:
                new LecturerServices().viewYourOwnCourses(username);
                Menu(username);
                break;
            default:
                break;

        }
    }





}
