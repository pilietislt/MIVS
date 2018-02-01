package mivs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import mivs.application.alert.Alert;
import mivs.services.CourseServices;
import mivs.services.StudentServices;
import mivs.users.Gender;
import mivs.users.Student;


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
    @FXML
    private Button addCourse;


    private Student student;

    public void init(String user) {
        makePaneInvisible();
        startPane.setVisible(true);
        student = new StudentServices().getStudent(user);
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

        int pNumber = 0;
        int mNumber = 0;

        if (!(personalNumber.getText().equals(""))) {
            pNumber = Integer.parseInt(personalNumber.getText());
        }
        if (!(mobileNumber.getText().trim().equals(""))) {
            mNumber = Integer.parseInt(mobileNumber.getText());
        }
        if (firstName.getText().equals("") || secondName.getText().equals("")) {
            new Alert().informationAlert("NO First name or Second name ", "Please fill First name and Second name");
            return;
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
        new StudentServices().editStudent(this.student, datePicker);
        viewPane();

    }

    public void viewCoursesPane() {
        makePaneInvisible();
        viewAllCousesPane.setVisible(true);
        courseList(new CourseServices().getAllCourseList());
    }

    public void viewMyCoursesPane() {
        makePaneInvisible();
        viewAllCousesPane.setVisible(true);
        courseList(new StudentServices().myCourseList(this.student));
    }

    public void registerToCoursesPane() {
        makePaneInvisible();
        registerToCoursesPane.setVisible(true);
        ObservableList<String> items = FXCollections.observableArrayList(new CourseServices().availableCourseList(student));
        availableCourseList.setItems(items);

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

    public void addCourseToStudentList() {

       new StudentServices().insetNewStudentCourse(this.student, availableCourseList);
        disableButton();
        registerToCoursesPane();

    }

    public void makeButtonActive() {

        if (!availableCourseList.getSelectionModel().isEmpty()) {
            addCourse.setDisable(false);

        }
    }

    private void disableButton() {
        addCourse.setDisable(true);
    }

}
