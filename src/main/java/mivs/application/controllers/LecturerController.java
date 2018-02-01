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
import mivs.services.LecturerServices;
import mivs.services.StudentServices;
import mivs.users.*;


public class LecturerController extends Controller {

    @FXML
    private Label firstLable;
    @FXML
    private Pane startPane;
    @FXML
    private Pane viewPane;
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
    private Pane viewMyCousesPane;
    @FXML
    private Pane viewStudentsPane;
    @FXML
    private TableView courseList;
    @FXML
    private TableColumn courseCodeCol;
    @FXML
    private TableColumn courseTitleCol;
    @FXML
    private TableColumn courseStartDateCol;
    @FXML
    private TableView studentList;
    @FXML
    private TableColumn studentCodeCol;
    @FXML
    private TableColumn studentFirstNameCol;
    @FXML
    private TableColumn studentSecondNameCol;


    private Lecturer lecturer;

    public void init(String user) {
        makePaneInvisible();
        startPane.setVisible(true);
        lecturer = new LecturerServices().getLecturer(user);
        firstLable.setText("Hello " + this.lecturer.getRole() + " " + this.lecturer.getFirstName());

    }

    public void viewPane() {

        gender.setItems(FXCollections.observableArrayList(Gender.MALE, Gender.FEMALE));
        makePaneInvisible();
        viewPane.setVisible(true);
        updateButton.setVisible(false);

        if (lecturer.getPersonalNumber() == 0) {
            personalNumber.setText("");

        } else {
            personalNumber.setText(Integer.toString(lecturer.getPersonalNumber()));
        }

        if (lecturer.getMobileNumber() == 0) {
            mobileNumber.setText("");
        } else {
            mobileNumber.setText(Integer.toString(lecturer.getMobileNumber()));
        }

        username.setText(lecturer.getUsername());
        firstName.setText(lecturer.getFirstName());
        firstName.setDisable(true);
        secondName.setText(lecturer.getSecondName());
        secondName.setDisable(true);

        personalNumber.setDisable(true);
        datePicker.setValue(lecturer.getDateOfBirth());
        datePicker.setDisable(true);
        email.setText(lecturer.getEmail());
        email.setDisable(true);

        mobileNumber.setDisable(true);
        address.setText(lecturer.getAddress());
        address.setDisable(true);
        gender.setValue(lecturer.getGender());
        gender.setDisable(true);
    }

    private void makePaneInvisible() {
        startPane.setVisible(false);
        viewPane.setVisible(false);
        viewMyCousesPane.setVisible(false);
        viewStudentsPane.setVisible(false);

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

        int pNumber = 0;
        int mNumber = 0;

        if (!(personalNumber.getText().equals(""))) {
            pNumber = Integer.parseInt(personalNumber.getText());
        }
        if (!(mobileNumber.getText().trim().equals(""))) {
            mNumber = Integer.parseInt(mobileNumber.getText());
        }

        this.lecturer.setFirstName(firstName.getText());
        this.lecturer.setSecondName(secondName.getText());
        this.lecturer.setPersonalNumber(pNumber);
        this.lecturer.setDateOfBirth(datePicker.getValue());
        this.lecturer.setEmail(email.getText());
        this.lecturer.setMobileNumber(mNumber);
        this.lecturer.setAddress(address.getText());

        if (gender.getValue() == null) {

        } else if (gender.getValue().equals(Gender.MALE)) {
            this.lecturer.setGender(Gender.MALE);
        } else if (gender.getValue().equals(Gender.FEMALE)) {
            this.lecturer.setGender(Gender.FEMALE);
        }
        new LecturerServices().editLecturer(lecturer,datePicker);
        viewPane();

    }

    public void viewMyCoursesPane() {
        makePaneInvisible();
        viewMyCousesPane.setVisible(true);
        courseList(new LecturerServices().lecturerCourseList(this.lecturer));
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

    public void viewStudentsPane() {

        makePaneInvisible();
        viewStudentsPane.setVisible(true);
        viewMyCourseList(new LecturerServices().lecturerCourseList(this.lecturer));
    }

    public void selectCourse() {
        if (courseList.getSelectionModel().getSelectedItem() != null) {
            Course selectedCourse = (Course) courseList.getSelectionModel().getSelectedItem();
            viewAllStudentsList(new StudentServices().allStudentsOfCourseList(selectedCourse.getCode()));
        }


    }

    private void viewMyCourseList(ObservableList data) {

        courseList.setItems(data);

        courseCodeCol.setCellValueFactory(new PropertyValueFactory("code"));
        courseTitleCol.setCellValueFactory(new PropertyValueFactory("tittle"));
        courseStartDateCol.setCellValueFactory(new PropertyValueFactory("startDate"));
        courseList.getColumns().setAll(courseCodeCol, courseTitleCol, courseStartDateCol);


    }

    private void viewAllStudentsList(ObservableList data) {

        studentList.setItems(data);

        studentCodeCol.setCellValueFactory(new PropertyValueFactory("studentCode"));
        studentFirstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        studentSecondNameCol.setCellValueFactory(new PropertyValueFactory("secondName"));
        studentList.getColumns().setAll(studentCodeCol, studentFirstNameCol, studentSecondNameCol);


    }


}
