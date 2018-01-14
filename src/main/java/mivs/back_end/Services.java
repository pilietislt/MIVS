package mivs.back_end;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mivs.courses.Course;
import mivs.users.*;
import mivs.users.Role;
import mivs.utils.*;

public class Services {
    public void addUser() {
        Role roles = null;
        System.out.println("Enter UserName:");
        String userName = ScannerUtils.scanString();
        userName = checkUnique(userName);
        System.out.println("Enter password");
        String password = ScannerUtils.scanString();
        System.out.println("Enter First Name:");
        String firstName = ScannerUtils.scanString();
        System.out.println("Enter SecondName");
        String secondName = ScannerUtils.scanString();
        System.out.println("Choose role");
        for (Role role : Role.values()) {
            System.out.println(role.get() + ". " + role);
        }
        int role = ScannerUtils.scanInt();
        switch (role) {
            case 1:
                addAdmin(userName, password, firstName, secondName);
                break;
            case 2:
                addLecturer(userName, password, firstName, secondName);

                break;
            case 3:
                addStudent(userName, password, firstName, secondName);
                break;
            default:
                System.out.println("wrong input");

                break;
        }

//        try {
//            HashMap<String, User> readUser = (HashMap<String, User> ) IOUtils.readObjectFromFile("files/users");
//            User user = new Student(userName, password, roles, firstName, secondName,genereteCode(firstName,secondName,roles));
//            readUser.put(userName, user);
//            IOUtils.writeObjectToFile(readUser,"files/users");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }


    public void addFirstAdmin() {

        System.out.println("Enter First Name:");
        String firstName = ScannerUtils.scanString();
        System.out.println("Enter SecondName");
        String secondName = ScannerUtils.scanString();
        User user = new Admin("admin", "admin", Role.ADMIN, firstName, secondName);
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put("admin", user);
        IOUtils.writeObjectToFile(userHashMap, "files/users");
    }

    private String checkUnique(String userName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            readUser.get(userName).getUsername();
        } catch (NullPointerException e) {
            return userName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("User alredy exist");
        System.out.println("Enter UserName:");
        userName = ScannerUtils.scanString();
        checkUnique(userName);
        return userName;
    }

    private String genereteCode(String firstName, String secondName, Role role) {
        Random generator = new Random();
        Integer rand1 = generator.nextInt(9);
        Integer rand2 = generator.nextInt(9);
        Integer rand3 = generator.nextInt(9);
        Integer i = role.get();
        Character a = firstName.toUpperCase().charAt(0);
        Character b = secondName.toUpperCase().charAt(0);
        String code = i.toString() + a.toString() + b.toString() + rand1.toString()+ rand2.toString()+ rand3.toString();
        return code;
    }

    private void addStudent(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Student(userName, password, Role.STUDENT, firstName, secondName, genereteCode(firstName, secondName, Role.STUDENT));
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addLecturer(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Lecturer(userName, password, Role.LECTURER, firstName, secondName, genereteCode(firstName, secondName, Role.LECTURER));
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addAdmin(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Admin(userName, password, Role.ADMIN, firstName, secondName);
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void addCourse(){

        if(getAllLecturer().size()== 0){
            System.out.println("Please firs add Lecturer");
            return;
        }



        System.out.println("Enter Tittle:");
        String title = ScannerUtils.scanString();
        System.out.println("Enter Description");
        String description = ScannerUtils.scanString();
        System.out.println("Enter Start Date:");
        LocalDate date = datePicker();
        System.out.println("Enter credit");
        int credit = ScannerUtils.scanInt();
        String lecturerCode = codeSelection();
        String code = genereteCode(title,description,Role.LECTURER);
        Course course = new Course(code, title, description, date, credit, lecturerCode);



        try {
            HashMap<String, Course> readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

           // User user = new Student(userName, password, Role.STUDENT, firstName, secondName, genereteCode(firstName, secondName, Role.STUDENT));
            readCourse.put(code, course);
            IOUtils.writeObjectToFile(readCourse, "files/courses");
        } catch (FileNotFoundException e) {
            HashMap<String, Course> newCourse = new HashMap<String, Course>();
            newCourse.put(code,course);
            IOUtils.writeObjectToFile(newCourse, "files/courses");
            //e.printStackTrace();
        }





    }

    public ArrayList< String> getAllLecturer(){
        ArrayList<String> codes = new ArrayList<>();
        HashMap<String, Lecturer> readLecturer = null;
        try {
            readLecturer = (HashMap<String, Lecturer>)IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Lecturer> entry : readLecturer.entrySet()) {
            //String key = entry.getKey();
            User value = entry.getValue();

            if(value.getRole().equals(Role.LECTURER)){
              //  System.out.println(readLecturer.get(value.getUsername()).getLecturerCode());
                codes.add(readLecturer.get(value.getUsername()).getFirstName() +" "+
                        readLecturer.get(value.getUsername()).getSecondName() +","+
                        readLecturer.get(value.getUsername()).getLecturerCode());
            }
        }
        return codes;
    }

    public String codeSelection (){
        String code = null;
        int i = 0;
        ArrayList<String> allLecturerCodes = getAllLecturer();
        for (String lcode: allLecturerCodes  ) {
            i += 1;
            System.out.println(i + ". " + lcode.substring(0, (lcode.length() - 7)));
        }
        System.out.println("Select Lectore");
        int choice = ScannerUtils.scanInt();
        if ( choice > 0 && choice <= allLecturerCodes.size()){
            return allLecturerCodes.get(choice-1).substring(allLecturerCodes.get(choice-1).length()-6);
        }else {
            System.out.println("Wrong choice");
            codeSelection();
            return null;
        }


    }

    public LocalDate datePicker(){


        System.out.println("Enter Year");
        int year = ScannerUtils.scanInt();
        System.out.println("Enter Month");
        int month = ScannerUtils.scanInt();
        System.out.println("Enter Day");
        int day = ScannerUtils.scanInt();


        int yearNow = LocalDate.now().getYear();
        int monthNow = LocalDate.now().getMonthValue();
        int dayNow = LocalDate.now().getDayOfMonth();

        return LocalDate.of(year,month,day);
    }

    public void courseList() {

        try {
            HashMap<String, Course> readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

            System.out.printf("%-7s %-10s %-13s %-10s %-10s\n", "Code", "Title", "StartDate", "Credit", "LecturerCode");
            for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
                Course value = entry.getValue();
                System.out.printf("%-7s %-10s %-13s %-10s %-10s\n", value.getCode(), value.getTittle(), value.getStartDate(), value.getCredit(), value.getLecturerCode());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No Courses Found");
            //e.printStackTrace();
        }


    }



}
