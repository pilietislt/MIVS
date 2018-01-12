package mivs.UI;

import mivs.back_end.Services;
import mivs.users.Gender;
import mivs.users.Lecturer;
import mivs.users.Role;

import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class LecturerUI {

    public void Menu (String username){
        HashMap<String, Lecturer> activeUser = null;

        try {
            activeUser = (HashMap<String, Lecturer> ) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello " +  activeUser.get(username).getRole()+ " "+ activeUser.get(username).getFirstName()+ " "+ activeUser.get(username).getLecturerCode());
        System.out.println("1. View info");
        System.out.println("2. Edit info");
        System.out.println("3. Exit");
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
            default:
                break;

        }
    }

    private void view(String username) {
        // System.out.printf("%-8s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
        //          "StudentId", "Username", "First name", "Second Name", "Per.Number", "Date Of Birth", "Email", "Mobile Number", "Gender", "Address");


        System.out.println("#. Lecturer Id: " + readLecturer(username).getLecturerCode());
        System.out.println("#. User  Name: " + readLecturer(username).getUsername());
        System.out.println("1. First Name: " + readLecturer(username).getFirstName());
        System.out.println("2. Second Name: " + readLecturer(username).getSecondName());

        if (readLecturer(username).getPersonalNumber() == 0) {
            System.out.println("3. Personal nr: unknown");
        } else {
            System.out.println("3. Personal nr: " + readLecturer(username).getPersonalNumber());
        }

        if (readLecturer(username).getDateOfBirth() == null) {
            System.out.println("4. Date Of Birth: unknown");
        } else {
            System.out.println("4. Date Of Birth: " + readLecturer(username).getDateOfBirth());
        }

        if (readLecturer(username).getEmail() == null) {
            System.out.println("5. Email : unknown");
        } else {
            System.out.println("5. Email : " + readLecturer(username).getEmail());
        }
        if (readLecturer(username).getMobileNumber() == 0) {
            System.out.println("6. Mobile nr: unknown");
        } else {
            System.out.println("6. Mobile nr: " + readLecturer(username).getMobileNumber());
        }

        if (readLecturer(username).getGender() == null) {
            System.out.println("7. Gender: unknown");
        } else {
            System.out.println("7. Gender: " + readLecturer(username).getGender());
        }
        if (readLecturer(username).getAddress() == null) {
            System.out.println("8. Address: unknown");
        } else {
            System.out.println("8. Address: " + readLecturer(username).getAddress());
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

    private Lecturer readLecturer(String username) {
        HashMap<String, Lecturer> readUser = null;

        try {
            readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return readUser.get(username);
    }

    private HashMap<String, Lecturer> readMapLecturer() {
        HashMap<String, Lecturer> readUser = null;

        try {
            readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return readUser;
    }

    private void editFirstName(String username) {
        System.out.println("Enter new First Name:");
        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    ScannerUtils.scanString(), readUser.get(username).getSecondName(), readUser.get(username).getLecturerCode());

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
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    readUser.get(username).getFirstName(), ScannerUtils.scanString(), readUser.get(username).getLecturerCode());
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
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getLecturerCode());
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
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getLecturerCode());

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
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getLecturerCode());

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
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getLecturerCode());
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
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getLecturerCode());
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
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
            Lecturer user = new Lecturer(username, readUser.get(username).getPassword(), Role.LECTURER,
                    readUser.get(username).getFirstName(), readUser.get(username).getSecondName(), readUser.get(username).getLecturerCode());
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


}
