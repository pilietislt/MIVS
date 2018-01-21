package mivs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import mivs.courses.Course;
import mivs.services.StudentServices;
import mivs.users.Gender;
import mivs.users.Student;
import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentController extends Controller {
    @FXML
    private Label firstLable;
    @FXML
    private Pane startPane;
    @FXML
    private Pane viewPane;
    @FXML
    private Pane viewAllCousesPane;
    @FXML
    private Pane registerToCoursesPane;
    @FXML
    private Button updateButton;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private TextField personalNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField mobileNumber;
    @FXML
    private TextArea address;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox gender;
    @FXML
    private TableColumn codeCol;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn startDateCol;
    @FXML
    private TableColumn creditsCol;
    @FXML
    private TableColumn lcodeCol;
    @FXML
    private TableColumn descriptionCol;
    @FXML
    private TableView coursesTable;
    @FXML
    private ListView availableCourseList;


    private Student student;

    public void init(String user) {
        makePaneInvisible();
        startPane.setVisible(true);

        HashMap<String, Student> readUser = null;
        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.student = readUser.get(user);
        firstLable.setText("Hello " + this.student.getRole() + " " + this.student.getFirstName());

    }

    public void viewPane() {

        gender.setItems(FXCollections.observableArrayList(Gender.MALE, Gender.FEMALE));
        makePaneInvisible();
        viewPane.setVisible(true);
        updateButton.setVisible(false);

        if (student.getPersonalNumber() == 0) {
            personalNumber.setText("");

        } else {
            personalNumber.setText(Integer.toString(student.getPersonalNumber()));
        }

        if (student.getMobileNumber() == 0) {
            mobileNumber.setText("");
        } else {
            mobileNumber.setText(Integer.toString(student.getMobileNumber()));
        }

        username.setText(student.getUsername());
        firstName.setText(student.getFirstName());
        firstName.setDisable(true);
        secondName.setText(student.getSecondName());
        secondName.setDisable(true);

        personalNumber.setDisable(true);
        datePicker.setValue(student.getDateOfBirth());
        datePicker.setDisable(true);
        email.setText(student.getEmail());
        email.setDisable(true);

        mobileNumber.setDisable(true);
        address.setText(student.getAddress());
        address.setDisable(true);
        gender.setValue(student.getGender());
        gender.setDisable(true);
    }

    private void makePaneInvisible() {
        startPane.setVisible(false);
        viewPane.setVisible(false);
        viewAllCousesPane.setVisible(false);
        registerToCoursesPane.setVisible(false);
    }

    public void editable() {
        viewPane();
        updateButton.setVisible(true);
        firstName.setDisable(false);
        secondName.setDisable(false);
        personalNumber.setDisable(false);
        datePicker.setDisable(false);
        email.setDisable(false);
        mobileNumber.setDisable(false);
        address.setDisable(false);
        gender.setDisable(false);

        personalNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    personalNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        mobileNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mobileNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void update() {

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");

            student = readUser.get(username.getText());
            int pNumber = 0;
            int mNumber = 0;

            if (!(personalNumber.getText().equals(""))) {
                pNumber = Integer.parseInt(personalNumber.getText());
            }
            if (!(mobileNumber.getText().trim().equals(""))) {
                mNumber = Integer.parseInt(mobileNumber.getText());
            }

            this.student.setFirstName(firstName.getText());
            this.student.setSecondName(secondName.getText());
            this.student.setPersonalNumber(pNumber);
            this.student.setDateOfBirth(datePicker.getValue());
            this.student.setEmail(email.getText());
            this.student.setMobileNumber(mNumber);
            this.student.setAddress(address.getText());

            if (gender.getValue() == null) {

            } else if (gender.getValue().equals(Gender.MALE)) {
                this.student.setGender(Gender.MALE);
            } else if (gender.getValue().equals(Gender.FEMALE)) {
                this.student.setGender(Gender.FEMALE);
            }


            readUser.put(username.getText(), this.student);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        viewPane();

    }

    public void viewCoursesPane() {
        makePaneInvisible();
        viewAllCousesPane.setVisible(true);
        courseList(getCourseList());
    }

    public void viewMyCoursesPane() {
        makePaneInvisible();
        viewAllCousesPane.setVisible(true);
        courseList(myCourseList());
    }

    public void registerToCoursesPane() {
        makePaneInvisible();
        registerToCoursesPane.setVisible(true);
        ObservableList<String> items = FXCollections.observableArrayList(availableCourseList());
        availableCourseList.setItems(items);
        // courseList(myCourseList());
    }

    private ObservableList<Course> getCourseList() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        try {
            HashMap<String, Course> readUser = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

            for (Map.Entry<String, Course> entry : readUser.entrySet()) {
                Course value = entry.getValue();
                courses.add(new Course(value.getCode(), value.getTittle(), value.getDescription(), value.getStartDate(), value.getCredit(), value.getLecturerCode()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private ObservableList<Course> myCourseList() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        try {
            HashMap<String, Course> readUser = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

            for (Map.Entry<String, Course> entry : readUser.entrySet()) {
                Course value = entry.getValue();
                for (String c : student.getRunningCourses()) {

                    if (value.getCode().equals(c)) {
                        courses.add(new Course(value.getCode(), value.getTittle(), value.getDescription(), value.getStartDate(), value.getCredit(), value.getLecturerCode()));

                    }

                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private void courseList(ObservableList data) {

        coursesTable.setItems(data);

        codeCol.setCellValueFactory(new PropertyValueFactory("code"));
        titleCol.setCellValueFactory(new PropertyValueFactory("tittle"));
        startDateCol.setCellValueFactory(new PropertyValueFactory("startDate"));
        creditsCol.setCellValueFactory(new PropertyValueFactory("credit"));
        lcodeCol.setCellValueFactory(new PropertyValueFactory("lecturerCode"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        coursesTable.getColumns().setAll(codeCol, titleCol, startDateCol, creditsCol, lcodeCol, descriptionCol);

    }

    private ArrayList<String> availableCourseList() {

        HashMap<String, Student> readUser = null;
        HashMap<String, Course> readCourse = null;
        ArrayList<String> availableCourse = new ArrayList<>();

        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");

        } catch (FileNotFoundException e) {

        }


        for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
            Course value = entry.getValue();
            if (value.getStartDate().isAfter(LocalDate.now()) &&
                    value.getCredit() < new StudentServices().getLeftCredit(this.student.getUsername()) &&
                    !readUser.get(this.student.getUsername()).getRunningCourses().contains(value.getCode())) {
                availableCourse.add(value.getTittle() + " " + value.getStartDate() + " " + value.getCredit() + " " + value.getCode());
            }

        }
        return availableCourse;
    }

    public void addCourseToStudentList() {

        String course = availableCourseList.getSelectionModel().getSelectedItem().toString();
        course = course.substring(course.length() - 6);

        try {
            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
            student = readUser.get(student.getUsername());
            student.getRunningCourses().add(course);
            readUser.put(student.getUsername(), student);
            IOUtils.writeObjectToFile(readUser, "files/users");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        registerToCoursesPane();

    }

}
