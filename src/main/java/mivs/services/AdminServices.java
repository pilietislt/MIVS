package mivs.services;

import mivs.courses.Course;
import mivs.users.*;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;
import mivs.back_end.Services;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminServices {

    public void addCourse() {

        if (new Services().getAllLecturer().size() == 0) {
            System.out.println("Please firs add Lecturer");
            return;
        }


        String title = ScannerUtils.scanString("Enter Tittle:");
        String description = ScannerUtils.scanString("Enter Description");

        LocalDate date = new Services().datePicker("Enter Start Date:");
        System.out.println();
        int credit = ScannerUtils.scanInt("Enter credit");
        String lecturerCode = codeSelection();
        String code = new Services().genereteCode(title, description, Role.LECTURER);
        Course course = new Course(code, title, description, date, credit, lecturerCode);

        try {
            HashMap<String, Course> readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");
            readCourse.put(code, course);
            IOUtils.writeObjectToFile(readCourse, "files/courses");
        } catch (FileNotFoundException e) {
            HashMap<String, Course> newCourse = new HashMap<String, Course>();
            newCourse.put(code, course);
            IOUtils.writeObjectToFile(newCourse, "files/courses");

        }


    }

    public void addCourseFX(String title, String description ,LocalDate date, int credit, String lcode) {


        String lecturerCode = codeSelectionFX(lcode);
        String code = new Services().genereteCode(title, description, Role.LECTURER);
        Course course = new Course(code, title, description, date, credit, lecturerCode);
        String username = new Services().getLecturerUsername(lecturerCode);

        try {
            HashMap<String, Course> readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");
            readCourse.put(code, course);
            IOUtils.writeObjectToFile(readCourse, "files/courses");
            addCourseToLecturer(username,code);

        } catch (FileNotFoundException e) {
            HashMap<String, Course> newCourse = new HashMap<String, Course>();
            newCourse.put(code, course);
            IOUtils.writeObjectToFile(newCourse, "files/courses");

        }

    }

    public String codeSelection() {

        int i = 0;
        ArrayList<String> allLecturerCodes = new Services().getAllLecturer();
        for (String lcode : allLecturerCodes) {
            i += 1;
            System.out.println(i + ". " + lcode.substring(0, (lcode.length() - 7)));
        }

        int choice = ScannerUtils.scanInt("Select Lectore", 0, allLecturerCodes.size());

        return allLecturerCodes.get(choice - 1).substring(allLecturerCodes.get(choice - 1).length() - 6);

    }
    public String codeSelectionFX(String code) {

        return code.substring(code.length() - 6);

    }

    public void addUser() {

        String userName = ScannerUtils.scanString("Enter UserName:");
        userName = new Services().checkUnique(userName);
        String password = ScannerUtils.scanString("Enter password");
        String firstName = ScannerUtils.scanString("Enter First Name:");
        String secondName = ScannerUtils.scanString("Enter SecondName");

        for (Role role : Role.values()) {
            System.out.println(role.get() + ". " + role);
        }

        switch (ScannerUtils.scanInt("Choose role: ",1,3)) {
            case 1:
                addAdmin(userName, password, firstName, secondName);
                break;
            case 2:
                addLecturer(userName, password, firstName, secondName);
                break;
            case 3:
                addStudent(userName, password, firstName, secondName);
                break;
        }
    }

    public void addStudent(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Student(userName, password, Role.STUDENT, firstName, secondName, new Services().genereteCode(firstName, secondName, Role.STUDENT));
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addLecturer(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Lecturer(userName, password, Role.LECTURER, firstName, secondName, new Services().genereteCode(firstName, secondName, Role.LECTURER));
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addAdmin(String userName, String password, String firstName, String secondName) {
        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            User user = new Admin(userName, password, Role.ADMIN, firstName, secondName);
            readUser.put(userName, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCourseToLecturer(String username, String code) {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer lecturer = readUser.get(username);
            lecturer.getRunningCourses().add(code);
            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
