package mivs.UI;

import mivs.back_end.Services;
import mivs.services.UserServices;
import mivs.users.Student;
import mivs.utils.*;
import mivs.services.StudentServices;


public class StudentUI {


    public void Menu(String username) {


        Student student = (Student) new UserServices().readUser(username);


        System.out.println("Hello " + student.getRole() + " " + student.getFirstName() + " " + student.getStudentCode());
        System.out.println("===================");
        System.out.println("   Student menu");
        System.out.println("===================");
        System.out.println("1. View info");
        System.out.println("2. Edit info");
        System.out.println("3. View all Course");
        System.out.println("4. Register to available course");
        System.out.println("5. View Your selected courses");
        System.out.println("6. Exit");

//        switch (ScannerUtils.scanInt()) {
//            case 1:
//                new StudentServices().view(username);
//                Menu(username);
//                break;
//            case 2:
//                new StudentServices().edit(username);
//                Menu(username);
//                break;
//            case 3:
//                new Services().courseList();
//                Menu(username);
//                break;
//            case 4:
//                new StudentServices().selectCourses(username);
//                Menu(username);
//                break;
//            case 5:
//                new StudentServices().viewAdded(username);
//                Menu(username);
//                break;
//            default:
//                break;
//
//        }
    }

}
