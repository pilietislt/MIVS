package mivs.services;

import mivs.back_end.Services;
import mivs.courses.Course;
import mivs.users.Student;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentServices {

    public void view(String username) {
        // System.out.printf("%-8s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
        //          "StudentId", "Username", "First name", "Second Name", "Per.Number", "Date Of Birth", "Email", "Mobile Number", "Gender", "Address");


        Student student = (Student) new UserServices().readUser(username);

        System.out.println("#. Student Id: " + student.getStudentCode());
        System.out.println("#. User  Name: " + student.getUsername());
        System.out.println("1. First Name: " + student.getFirstName());
        System.out.println("2. Second Name: " + student.getSecondName());

        if (student.getPersonalNumber() == 0) {
            System.out.println("3. Personal nr: unknown");
        } else {
            System.out.println("3. Personal nr: " + student.getPersonalNumber());
        }

        if (student.getDateOfBirth() == null) {
            System.out.println("4. Date Of Birth: unknown");
        } else {
            System.out.println("4. Date Of Birth: " + student.getDateOfBirth());
        }

        if (student.getEmail() == null) {
            System.out.println("5. Email : unknown");
        } else {
            System.out.println("5. Email : " + student.getEmail());
        }
        if (student.getMobileNumber() == 0) {
            System.out.println("6. Mobile nr: unknown");
        } else {
            System.out.println("6. Mobile nr: " + student.getMobileNumber());
        }

        if (student.getGender() == null) {
            System.out.println("7. Gender: unknown");
        } else {
            System.out.println("7. Gender: " + student.getGender());
        }
        if (student.getAddress() == null) {
            System.out.println("8. Address: unknown");
        } else {
            System.out.println("8. Address: " + student.getAddress());
        }


    }

    public void edit(String username) {

        view(username);
        switch (ScannerUtils.scanInt("9. Back", 1, 9)) {
            case 1:
                editFirstName(username);
                edit(username);
                break;
            case 2:
                editSecondName(username);
                edit(username);
                break;
            case 3:
                editPersonalNr(username);
                edit(username);
                break;
            case 4:
                editDateOfBirth(username);
                edit(username);
                break;
            case 5:
                editEmail(username);
                edit(username);
                break;
            case 6:
                editMobileNr(username);
                edit(username);
                break;
            case 7:
                editGender(username);
                edit(username);
                break;
            case 8:
                editAddress(username);
                edit(username);
                break;
            case 9:
                break;

        }


    }

    private void editFirstName(String username) {

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setFirstName(ScannerUtils.scanString("Enter new First Name:"));

            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editSecondName(String username) {

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setSecondName(ScannerUtils.scanString("Enter new Second Name:"));

            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editPersonalNr(String username) {

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setPersonalNumber(ScannerUtils.scanInt("Enter new Personal Nr.:"));


            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editDateOfBirth(String username) {

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setDateOfBirth(new Services().datePicker("Enter new Date Of Birth:"));

            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editEmail(String username) {
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setEmail(ScannerUtils.scanString("Enter new Email:"));

            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editGender(String username) {

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setGender(new UserServices().selectGender());

            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editMobileNr(String username) {
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setMobileNumber(ScannerUtils.scanInt("Enter new Mobile Nr:"));

            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editAddress(String username) {

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            Student student = readUser.get(username);
            student.setAddress(ScannerUtils.scanString("Enter new Address:"));

            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void selectCourses(String username) {
        int i = 0;
        String course = null;
        ArrayList<String> availableCourse = new ArrayList<>();
        HashMap<String, Student> readUser = null;
        HashMap<String, Course> readCourse = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

        } catch (FileNotFoundException e) {
            System.out.println("No Courses Found");
            return;
        }

        for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
            Course value = entry.getValue();
            if (value.getStartDate().isAfter(LocalDate.now()) &&
                    value.getCredit() < getLeftCredit(username) &&
                    !readUser.get(username).getRunningCourses().contains(value.getCode())) {
                availableCourse.add(value.getTittle() + " " + value.getStartDate() + " " + value.getCredit() + " " + value.getCode());
            }

        }


        if (availableCourse.size() == 0) {
            System.out.println("No available courses");
            return;
        }

        for (String ac : availableCourse) {
            i += 1;
            System.out.println(i + ". " + ac);
        }

        int choice = ScannerUtils.scanInt("Select Course", 0, availableCourse.size());
        course = availableCourse.get(choice - 1).substring(availableCourse.get(choice - 1).length() - 6);
        addCourseToStudentList(username, course);
    }

    private void addCourseToStudentList(String username, String course) {

        HashMap<String, Student> readUser = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Student student = readUser.get(username);
        student.getRunningCourses().add(course);
        readUser.put(username, student);
        IOUtils.writeObjectToFile(readUser, "files/users");

    }

    public void viewAdded(String username) {

        HashMap<String, Student> readUser = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < readUser.get(username).getRunningCourses().size(); i++) {

            System.out.println((i + 1) + ". " + readUser.get(username).getRunningCourses().get(i));

        }
    }

    public int getLeftCredit(String username) {

        HashMap<String, Student> readUser = null;
        HashMap<String, Course> readCourse = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

        } catch (FileNotFoundException e) {
            System.out.println("No Courses Found");
        }

        int credit = 0;
        try {
            for (String s : readUser.get(username).getRunningCourses()) {

                credit += readCourse.get(s).getCredit();

            }

            return 12 - credit;
        } catch (NullPointerException e) {
            return 12;
        }


    }
}
