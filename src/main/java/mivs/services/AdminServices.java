package mivs.services;

import mivs.courses.Course;
import mivs.db.DB;
import mivs.users.*;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;
import mivs.back_end.Services;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public void addCourseFX(String title, String description, LocalDate date, int credit, String lcode) {


        String lecturerCode = codeSelectionFX(lcode);
        String code = new Services().genereteCode(title, description, Role.LECTURER);
      //  Course course = new Course(code, title, description, date, credit, lecturerCode);
        String username = new Services().getLecturerUsername(lecturerCode);

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "insert into course (course_code, course_title, course_description, course_startDate, course_credit, course_lecturerCode) values (?,?,?,?,?,?);"

            );
            statement.setString(1, code);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setDate(4, Date.valueOf(date));
            statement.setInt(5,credit);
            statement.setString(6, lecturerCode);
            statement.execute();
            statement.execute("insert into runningcourses (course_code, user_code) values ('"+code+"','"+lecturerCode+"');");

            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


//        try {
//            HashMap<String, Course> readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");
//            readCourse.put(code, course);
//            IOUtils.writeObjectToFile(readCourse, "files/courses");
//            addCourseToLecturer(username, code);
//
//        } catch (FileNotFoundException e) {
//            HashMap<String, Course> newCourse = new HashMap<String, Course>();
//            newCourse.put(code, course);
//            IOUtils.writeObjectToFile(newCourse, "files/courses");
//
//        }

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

        switch (ScannerUtils.scanInt("Choose role: ", 1, 3)) {
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
        String studentCode = new Services().genereteCode(firstName, secondName, Role.STUDENT);
        new DB().insertStudentToDb(userName, password, firstName, secondName, 3,studentCode );

    }

    public void addLecturer(String userName, String password, String firstName, String secondName) {
        String lecturerCode = new Services().genereteCode(firstName, secondName, Role.LECTURER);
        new DB().insertLecturerToDb(userName, password, firstName, secondName, 2,lecturerCode);
    }

    public void addAdmin(String userName, String password, String firstName, String secondName) {
        new DB().insertUserToDb(userName, password, firstName, secondName, 1);
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
