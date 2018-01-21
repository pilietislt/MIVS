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


    public void addFirstAdmin() {

        String firstName = ScannerUtils.scanString("Enter First Name:");
        String secondName = ScannerUtils.scanString("Enter SecondName");
        User user = new Admin("admin", "admin", Role.ADMIN, firstName, secondName);
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put("admin", user);
        IOUtils.writeObjectToFile(userHashMap, "files/users");
    }
    public void addFirstAdminFX(String firstName, String secondName) {

        User user = new Admin("admin", "admin", Role.ADMIN, firstName, secondName);
        HashMap<String, User> userHashMap = new HashMap<String, User>();
        userHashMap.put("admin", user);
        IOUtils.writeObjectToFile(userHashMap, "files/users");
    }

    public String checkUnique(String userName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            readUser.get(userName).getUsername();
        } catch (NullPointerException e) {
            return userName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        userName = ScannerUtils.scanString("User alredy exist enter new UserName:");
        checkUnique(userName);
        return userName;
    }

    public Boolean checkUniqueBoolen (String userName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            readUser.get(userName).getUsername();
        } catch (NullPointerException e) {
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       // userName = ScannerUtils.scanString("User alredy exist enter new UserName:");
       // checkUnique(userName);
        return false;
    }

    public String genereteCode(String firstName, String secondName, Role role) {
        Random generator = new Random();
        Integer rand1 = generator.nextInt(9);
        Integer rand2 = generator.nextInt(9);
        Integer rand3 = generator.nextInt(9);
        Integer i = role.get();
        Character a = firstName.toUpperCase().charAt(0);
        Character b = secondName.toUpperCase().charAt(0);
        String code = i.toString() + a.toString() + b.toString() + rand1.toString() + rand2.toString() + rand3.toString();
        return code;
    }

    public ArrayList<String> getAllLecturer() {
        ArrayList<String> codes = new ArrayList<>();
        HashMap<String, Lecturer> readLecturer = null;
        try {
            readLecturer = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Lecturer> entry : readLecturer.entrySet()) {
            //String key = entry.getKey();
            User value = entry.getValue();

            if (value.getRole().equals(Role.LECTURER)) {
                //  System.out.println(readLecturer.get(value.getUsername()).getLecturerCode());
                codes.add(readLecturer.get(value.getUsername()).getFirstName() + " " +
                        readLecturer.get(value.getUsername()).getSecondName() + "," +
                        readLecturer.get(value.getUsername()).getLecturerCode());
            }
        }
        return codes;
    }

    public String getLecturerUsername(String code) {

        HashMap<String, Lecturer> readLecturer = null;
        try {
            readLecturer = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Lecturer> entry : readLecturer.entrySet()) {
            //String key = entry.getKey();
            User value = entry.getValue();

            if (value.getRole().equals(Role.LECTURER)) {

                if(readLecturer.get(value.getUsername()).getLecturerCode().equals(code)){
                    return value.getUsername();
                }


                //  System.out.println(readLecturer.get(value.getUsername()).getLecturerCode());
               // codes.add(readLecturer.get(value.getUsername()).getUsername()+ "" +
               //         readLecturer.get(value.getUsername()).getLecturerCode());
            }
        }
        return null;
    }

    public LocalDate datePicker(String messege) {
        System.out.println(messege);


        //System.out.println("Enter Year");
        int year = ScannerUtils.scanInt("Enter Year",2017,2019);
        System.out.println("Enter Month");
        int month = ScannerUtils.scanInt("Enter Month");
        System.out.println("Enter Day");
        int day = ScannerUtils.scanInt();


        int yearNow = LocalDate.now().getYear();
        int monthNow = LocalDate.now().getMonthValue();
        int dayNow = LocalDate.now().getDayOfMonth();

        return LocalDate.of(year, month, day);
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
