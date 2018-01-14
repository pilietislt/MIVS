package mivs.UI;

import mivs.back_end.Services;
import mivs.courses.Course;
import mivs.users.Gender;
import mivs.users.Role;
import mivs.users.Student;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.text.Format;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentUI {


    public void Menu(String username) {


        System.out.println("Hello " + readStudent(username).getRole() + " " + readStudent(username).getFirstName() + " " + readStudent(username).getStudentCode());
        System.out.println("1. View info");
        System.out.println("2. Edit info");
        System.out.println("3. View all Course");
        System.out.println("4. Register to available course");
        System.out.println("5. Exit");
        int choice = ScannerUtils.scanInt();

        switch (choice) {
            case 1:
                view(username);
                Menu(username);
                break;
            case 2:
                edit(username);
                Menu(username);
                break;
            case 3:
                new Services().courseList();
                Menu(username);
                break;
            case 4:
                selectCoures( username);

                // addCoursetoStudentList(username, "ereer");

                Menu(username);
                break;
            case 5:
                viewaddet(username);
                Menu(username);
                break;
            default:
                break;

        }
    }

    private void view(String username) {
        // System.out.printf("%-8s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
        //          "StudentId", "Username", "First name", "Second Name", "Per.Number", "Date Of Birth", "Email", "Mobile Number", "Gender", "Address");


        System.out.println("#. Student Id: " + readStudent(username).getStudentCode());
        System.out.println("#. User  Name: " + readStudent(username).getUsername());
        System.out.println("1. First Name: " + readStudent(username).getFirstName());
        System.out.println("2. Second Name: " + readStudent(username).getSecondName());

        if (readStudent(username).getPersonalNumber() == 0) {
            System.out.println("3. Personal nr: unknown");
        } else {
            System.out.println("3. Personal nr: " + readStudent(username).getPersonalNumber());
        }

        if (readStudent(username).getDateOfBirth() == null) {
            System.out.println("4. Date Of Birth: unknown");
        } else {
            System.out.println("4. Date Of Birth: " + readStudent(username).getDateOfBirth());
        }

        if (readStudent(username).getEmail() == null) {
            System.out.println("5. Email : unknown");
        } else {
            System.out.println("5. Email : " + readStudent(username).getEmail());
        }
        if (readStudent(username).getMobileNumber() == 0) {
            System.out.println("6. Mobile nr: unknown");
        } else {
            System.out.println("6. Mobile nr: " + readStudent(username).getMobileNumber());
        }

        if (readStudent(username).getGender() == null) {
            System.out.println("7. Gender: unknown");
        } else {
            System.out.println("7. Gender: " + readStudent(username).getGender());
        }
        if (readStudent(username).getAddress() == null) {
            System.out.println("8. Address: unknown");
        } else {
            System.out.println("8. Address: " + readStudent(username).getAddress());
        }


    }

    private void edit(String username) {

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

    private Student readStudent(String username) {
        HashMap<String, Student> readUser = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return readUser.get(username);
    }

    private HashMap<String, Student> readMapStudent() {
        HashMap<String, Student> readUser = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return readUser;
    }

    private void editFirstName(String username) {
        System.out.println("Enter new First Name:");
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    ScannerUtils.scanString(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());

            user.setPersonalNumber(readUser.get(username).getPersonalNumber());
            user.setDateOfBirth(readUser.get(username).getDateOfBirth());
            user.setEmail(readUser.get(username).getEmail());
            user.setMobileNumber(readUser.get(username).getMobileNumber());
            user.setGender(readUser.get(username).getGender());
            user.setAddress(readUser.get(username).getAddress());

            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editSecondName(String username) {
        System.out.println("Enter new Second Name:");
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), ScannerUtils.scanString(), readUser.get(username).getStudentCode());
            user.setPersonalNumber(readUser.get(username).getPersonalNumber());
            user.setDateOfBirth(readUser.get(username).getDateOfBirth());
            user.setEmail(readUser.get(username).getEmail());
            user.setMobileNumber(readUser.get(username).getMobileNumber());
            user.setGender(readUser.get(username).getGender());
            user.setAddress(readUser.get(username).getAddress());
            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editPersonalNr(String username) {
        System.out.println("Enter new Personal Nr.:");
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());
            user.setPersonalNumber(ScannerUtils.scanInt());
            user.setDateOfBirth(readUser.get(username).getDateOfBirth());
            user.setEmail(readUser.get(username).getEmail());
            user.setMobileNumber(readUser.get(username).getMobileNumber());
            user.setGender(readUser.get(username).getGender());
            user.setAddress(readUser.get(username).getAddress());

            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editDateOfBirth(String username) {
        System.out.println("Enter new Date Of Birth:");
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());

            user.setPersonalNumber(readUser.get(username).getPersonalNumber());
            user.setDateOfBirth(new Services().datePicker());
            user.setEmail(readUser.get(username).getEmail());
            user.setMobileNumber(readUser.get(username).getMobileNumber());
            user.setGender(readUser.get(username).getGender());
            user.setAddress(readUser.get(username).getAddress());


            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editEmail(String username) {
        System.out.println("Enter new Email:");
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());

            user.setPersonalNumber(readUser.get(username).getPersonalNumber());
            user.setDateOfBirth(readUser.get(username).getDateOfBirth());
            user.setEmail(ScannerUtils.scanString());
            user.setMobileNumber(readUser.get(username).getMobileNumber());
            user.setGender(readUser.get(username).getGender());
            user.setAddress(readUser.get(username).getAddress());

            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editGender(String username) {
        System.out.println("Select Gender:");
        Gender gen = null;
        for (Gender gender : Gender.values()) {
            System.out.println(gender.get() + ". " + gender);
        }
        int gender = ScannerUtils.scanInt();
        switch (gender) {
            case 1:
                gen = Gender.FEMALE;
                break;
            case 2:
                gen = Gender.MALE;
                break;
            default:
                System.out.println("wrong input");
                break;

        }

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());
            user.setPersonalNumber(readUser.get(username).getPersonalNumber());
            user.setDateOfBirth(readUser.get(username).getDateOfBirth());
            user.setEmail(readUser.get(username).getEmail());


            user.setMobileNumber(readUser.get(username).getMobileNumber());
            user.setGender(gen);
            user.setAddress(readUser.get(username).getAddress());

            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editMobileNr(String username) {
        System.out.println("Enter new Mobile Nr:");
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());
            user.setPersonalNumber(readUser.get(username).getPersonalNumber());
            user.setDateOfBirth(readUser.get(username).getDateOfBirth());
            user.setEmail(readUser.get(username).getEmail());
            user.setMobileNumber(ScannerUtils.scanInt());
            user.setGender(readUser.get(username).getGender());
            user.setAddress(readUser.get(username).getAddress());

            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void editAddress(String username) {
        System.out.println("Enter new Address:");
        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            Student user = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());
            user.setPersonalNumber(readUser.get(username).getPersonalNumber());
            user.setDateOfBirth(readUser.get(username).getDateOfBirth());
            user.setEmail(readUser.get(username).getEmail());
            user.setMobileNumber(readUser.get(username).getMobileNumber());
            user.setGender(readUser.get(username).getGender());
            user.setAddress(ScannerUtils.scanString());

            readUser.put(username, user);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void selectCoures(String username) {
        ArrayList<String> availableCourse = new ArrayList<>();
        HashMap<String, Student> readUser = null;
        HashMap<String, Course> readCourse = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

        } catch (FileNotFoundException e) {
            System.out.println("No Courses Found");
        }

        try {
            for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
                Course value = entry.getValue();
                if (value.getStartDate().isAfter(LocalDate.now()) &&
                        value.getCredit() < getLeftCredit(username) &&
                        !readUser.get(username).getRunningCourses().contains(value.getCode())) {
                    availableCourse.add(value.getTittle() + " " + value.getStartDate() + " " + value.getCredit() + " " + value.getCode());
                }

            }
        } catch (NullPointerException e) {
            for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
                Course value = entry.getValue();
                if (value.getStartDate().isAfter(LocalDate.now()) &&
                        value.getCredit() < getLeftCredit(username) ) {
                    availableCourse.add(value.getTittle() + " " + value.getStartDate() + " " + value.getCredit() + " " + value.getCode());
                }

            }
        }


        int i = 0;
        String course = null;

        if (availableCourse.size() == 0) {
            System.out.println("No available courses");

            return;

        }


        for (String a : availableCourse) {
            i += 1;
            System.out.println(i + ". " + a);

        }
        System.out.println("Select Course");
        int choice = ScannerUtils.scanInt();
        if (choice > 0 && choice <= availableCourse.size()) {
            course = availableCourse.get(choice - 1).substring(availableCourse.get(choice - 1).length() - 6);
        } else {
            System.out.println("Wrong choice");
            return;
            //selectCoures(12);

        }

        addCoursetoStudentList(username, course);


    }

    public void addCoursetoStudentList(String username, String course) {
        Student student = null;
        HashMap<String, Student> readUser = null;
        ArrayList<String> list = new ArrayList<>();


        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            student = new Student(username, readUser.get(username).getPassword(), Role.STUDENT,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getStudentCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        student.setPersonalNumber(readUser.get(username).getPersonalNumber());
        student.setDateOfBirth(readUser.get(username).getDateOfBirth());
        student.setEmail(readUser.get(username).getEmail());
        student.setMobileNumber(readUser.get(username).getMobileNumber());
        student.setGender(readUser.get(username).getGender());
        student.setAddress(readUser.get(username).getAddress());


        //student.setRunningCourses(readUser.get(username).getRunningCourses());
        try {
            readUser.get(username).getRunningCourses().size();
        } catch (NullPointerException e) {

            student.setRunningCourses(list);
            student.getRunningCourses().add(course);
            readUser.put(username, student);
            IOUtils.writeObjectToFile(readUser, "files/users");
            return;
            //readUser.get(username).setRunningCourses(list);
            // readUser.get(username).getRunningCourses().add(0,"test");

        }
        student.setRunningCourses(readUser.get(username).getRunningCourses());

        student.getRunningCourses().add(course);

//        for (int i = 0; i < student.getRunningCourses().size(); i++) {
//            System.out.println(student.getRunningCourses().size());
//            System.out.println(student.getRunningCourses().get(i));
//        }


        //student.setRunningCourses(null);
        readUser.put(username, student);
        IOUtils.writeObjectToFile(readUser, "files/users");

    }

    public void viewaddet(String username) {

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

    private int getLeftCredit(String username){

        HashMap<String, Student> readUser = null;
        HashMap<String, Course> readCourse = null;

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

        } catch (FileNotFoundException e) {
            System.out.println("No Courses Found");

        }
        int credit = 0;
        try{
            for (String s: readUser.get(username).getRunningCourses() ) {

                credit += readCourse.get(s).getCredit();

            }

            return 12-credit;
        }catch (NullPointerException e){
            return 12;
        }


    }


}
