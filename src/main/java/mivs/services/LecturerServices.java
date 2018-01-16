package mivs.services;

import mivs.back_end.Services;
import mivs.courses.Course;
import mivs.users.Lecturer;
import mivs.utils.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class LecturerServices {


    public void view(String username) {
        // System.out.printf("%-8s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
        //          "StudentId", "Username", "First name", "Second Name", "Per.Number", "Date Of Birth", "Email", "Mobile Number", "Gender", "Address");

        Lecturer lecturer = (Lecturer) new UserServices().readUser(username);

        System.out.println("#. Lecturer Id: " + lecturer.getLecturerCode());
        System.out.println("#. User  Name: " + lecturer.getUsername());
        System.out.println("1. First Name: " + lecturer.getFirstName());
        System.out.println("2. Second Name: " + lecturer.getSecondName());

        if (lecturer.getPersonalNumber() == 0) {
            System.out.println("3. Personal nr: unknown");
        } else {
            System.out.println("3. Personal nr: " + lecturer.getPersonalNumber());
        }

        if (lecturer.getDateOfBirth() == null) {
            System.out.println("4. Date Of Birth: unknown");
        } else {
            System.out.println("4. Date Of Birth: " + lecturer.getDateOfBirth());
        }

        if (lecturer.getEmail() == null) {
            System.out.println("5. Email : unknown");
        } else {
            System.out.println("5. Email : " + lecturer.getEmail());
        }
        if (lecturer.getMobileNumber() == 0) {
            System.out.println("6. Mobile nr: unknown");
        } else {
            System.out.println("6. Mobile nr: " + lecturer.getMobileNumber());
        }

        if (lecturer.getGender() == null) {
            System.out.println("7. Gender: unknown");
        } else {
            System.out.println("7. Gender: " + lecturer.getGender());
        }
        if (lecturer.getAddress() == null) {
            System.out.println("8. Address: unknown");
        } else {
            System.out.println("8. Address: " + lecturer.getAddress());
        }


    }

    public void edit(String username) {

        view(username);
        System.out.println("9. Back");
        switch (ScannerUtils.scanInt()) {
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

            default:
                break;

        }


    }

    private void editFirstName(String username) {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setFirstName(ScannerUtils.scanString("Enter new First Name:"));

            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editSecondName(String username) {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setSecondName(ScannerUtils.scanString("Enter new Second Name:"));

            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editPersonalNr(String username) {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setPersonalNumber(ScannerUtils.scanInt("Enter new Personal Nr.:"));


            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editDateOfBirth(String username) {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setDateOfBirth(new Services().datePicker("Enter new Date Of Birth:"));

            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editEmail(String username) {
        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setEmail(ScannerUtils.scanString("Enter new Email:"));

            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editGender(String username) {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setGender(new UserServices().selectGender());

            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editMobileNr(String username) {
        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setMobileNumber(ScannerUtils.scanInt("Enter new Mobile Nr:"));

            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editAddress(String username) {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            Lecturer lecturer = readUser.get(username);
            lecturer.setAddress(ScannerUtils.scanString("Enter new Address:"));

            readUser.put(username, lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void viewYourOwnCourses(String username) {

        HashMap<String, Lecturer> readUser = null;
        HashMap<String, Course> readCourse = null;

        try {
            readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

        } catch (FileNotFoundException e) {
            System.out.println("No Courses Found");

        }
        System.out.printf("%-7s %-10s %-13s %-10s %-10s\n", "Code", "Title", "StartDate", "Credit", "LecturerCode");

        for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
            //String key = entry.getKey();
            Course value = entry.getValue();

            if (value.getLecturerCode().equals(readUser.get(username).getLecturerCode())) {

                System.out.printf("%-7s %-10s %-13s %-10s %-10s\n", value.getCode(), value.getTittle(), value.getStartDate(), value.getCredit(), value.getLecturerCode());
            }
        }


    }
}
