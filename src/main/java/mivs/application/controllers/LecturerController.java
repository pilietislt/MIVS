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
import mivs.db.DB;
import mivs.users.*;
import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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

        try {
            Connection con = new DB().connection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select u.user_username, user_password, user_firstName, user_secondName, lecturer_lecturerCode, lecturer_personalNumber, lecturer_dateOfBirth , lecturer_email , lecturer_mobileNumber , gender_id , lecturer_address  from user u,lecturer l where l.user_username = u.user_username AND l.user_username ='" + user + "';");
            while (rs.next()) {
                String userName = rs.getString(1);
                String password = rs.getString(2);
                String firstName = rs.getString(3);
                String secondName = rs.getString(4);
                String lecturerCode = rs.getString(5);
                int personalNumber = rs.getInt(6);
                String email = rs.getString(8);
                int mobileNUmber = rs.getInt(9);
                int gender = rs.getInt(10);
                String address = rs.getString(11);


                lecturer = new Lecturer(userName, password, Role.LECTURER, firstName, secondName, lecturerCode);
                lecturer.setPersonalNumber(personalNumber);
                if (rs.getDate(7) != null) {
                    lecturer.setDateOfBirth(rs.getDate(7).toLocalDate());
                }
                lecturer.setEmail(email);

                lecturer.setMobileNumber(mobileNUmber);
                lecturer.setAddress(address);
                if (gender == 1) {
                    lecturer.setGender(Gender.FEMALE);
                } else if (gender == 2) {
                    lecturer.setGender(Gender.MALE);
                }

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //  this.lecturer = readUser.get(user);
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

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement("update user set user_firstName = ? , user_secondName = ? where user_username = ?;");
            statement.setString(1, this.lecturer.getFirstName());
            statement.setString(2, this.lecturer.getSecondName());
            statement.setString(3, this.lecturer.getUsername());
            statement.execute();
            statement = connection.prepareStatement("update lecturer set lecturer_personalNumber = ?, lecturer_dateOfBirth =?, lecturer_email = ?, lecturer_mobileNumber = ?, gender_id =?, lecturer_address =? where user_username = ?; ");
            statement.setInt(1, this.lecturer.getPersonalNumber());
            if (datePicker.getValue() == null) {
                statement.setNull(2, Types.DATE);
            } else {
                statement.setDate(2, Date.valueOf(this.lecturer.getDateOfBirth()));
            }
            statement.setString(3, this.lecturer.getEmail());
            statement.setInt(4, this.lecturer.getMobileNumber());
            if (this.lecturer.getGender() == Gender.FEMALE) {
                statement.setInt(5, 1);
            } else if (this.lecturer.getGender() == Gender.MALE) {
                statement.setInt(5, 2);
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, this.lecturer.getAddress());
            statement.setString(7, this.lecturer.getUsername());
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }

        viewPane();

    }

    public void viewMyCoursesPane() {
        makePaneInvisible();
        viewMyCousesPane.setVisible(true);
        courseList(myCourseList());
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

    private ObservableList<Course> myCourseList() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                            " * \n" +
                            "FROM  \n" +
                            " course c,\n" +
                            " lecturerrunningcourses l\n" +
                            "WHERE \n" +
                            " l.course_code = c.course_code and \n" +
                            " lecturer_code = ?  ;");
            statement.setString(1, this.lecturer.getLecturerCode());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4).toLocalDate(), resultSet.getInt(5), resultSet.getString(6)));
            }


            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }

//        try {
//            HashMap<String, Course> readUser = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");
//
//            for (Map.Entry<String, Course> entry : readUser.entrySet()) {
//                Course value = entry.getValue();
//                for (String c : lecturer.getRunningCourses()) {
//
//                    if (value.getCode().equals(c)) {
//                        courses.add(new Course(value.getCode(), value.getTittle(), value.getDescription(), value.getStartDate(), value.getCredit(), value.getLecturerCode()));
//
//                    }
//
//                }
//
//
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        return courses;
    }

    public void viewStudentsPane() {

        makePaneInvisible();
        viewStudentsPane.setVisible(true);
        viewMyCourseList(myCourseList());
    }

    public void selectCourse() {
        if (courseList.getSelectionModel().getSelectedItem() != null) {
            Course selectedCourse = (Course) courseList.getSelectionModel().getSelectedItem();
            viewAllStudentsList(allStudentsList(selectedCourse.getCode()));
        }


    }

    private void viewMyCourseList(ObservableList data) {

        courseList.setItems(data);

        courseCodeCol.setCellValueFactory(new PropertyValueFactory("code"));
        courseTitleCol.setCellValueFactory(new PropertyValueFactory("tittle"));
        courseStartDateCol.setCellValueFactory(new PropertyValueFactory("startDate"));
        courseList.getColumns().setAll(courseCodeCol, courseTitleCol, courseStartDateCol);


    }

    private ObservableList<Student> allStudentsList(String code) {
        ObservableList<Student> students = FXCollections.observableArrayList();


        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                            " u.user_username,\n" +
                            " user_password,\n" +
                            " user_firstName,\n" +
                            " user_secondName,\n" +
                            " s.student_studentCode\n" +
                            "FROM  \n" +
                            " user u,\n" +
                            " student s,\n" +
                            " studentrunningcourses sr\n" +
                            "WHERE\n" +
                            " u.user_username = s.user_username and\n" +
                            " s.student_studentCode = sr.student_code and\n" +
                            " sr.course_code = ? ;"
            );
            statement.setString(1, code);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString(1), resultSet.getString(2), Role.STUDENT, resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }


            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return students;
    }

    private void viewAllStudentsList(ObservableList data) {

        studentList.setItems(data);

        studentCodeCol.setCellValueFactory(new PropertyValueFactory("studentCode"));
        studentFirstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        studentSecondNameCol.setCellValueFactory(new PropertyValueFactory("secondName"));
        studentList.getColumns().setAll(studentCodeCol, studentFirstNameCol, studentSecondNameCol);


    }


}
