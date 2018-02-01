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
import mivs.courses.Course;
import mivs.db.DB;
import mivs.services.StudentServices;

import mivs.users.Gender;
import mivs.users.Role;
import mivs.users.Student;
import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.sql.*;
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

        try {
            Connection con = new DB().connection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select u.user_username, user_password, user_firstName, user_secondName, student_studentCode, student_personalNumber, student_dateOfBirth , student_email , student_mobileNumber , gender_id , student_address  from user u,student s where s.user_username = u.user_username AND s.user_username ='" + user + "';");
            while (rs.next()) {
                String userName = rs.getString(1);
                String password = rs.getString(2);
                String firstName = rs.getString(3);
                String secondName = rs.getString(4);
                String studentCode = rs.getString(5);
                int personalNumber = rs.getInt(6);
                String email = rs.getString(8);
                int mobileNUmber = rs.getInt(9);
                int gender = rs.getInt(10);
                String address = rs.getString(11);


                student = new Student(userName, password, Role.STUDENT, firstName, secondName, studentCode);
                student.setPersonalNumber(personalNumber);
                if (rs.getDate(7) != null) {
                    student.setDateOfBirth(rs.getDate(7).toLocalDate());
                }
                student.setEmail(email);

                student.setMobileNumber(mobileNUmber);
                student.setAddress(address);
                if (gender == 1) {
                    student.setGender(Gender.FEMALE);
                } else if (gender == 2) {
                    student.setGender(Gender.MALE);
                }

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement("update user set user_firstName = ? , user_secondName = ? where user_username = ?;");
            statement.setString(1, this.student.getFirstName());
            statement.setString(2, this.student.getSecondName());
            statement.setString(3, this.student.getUsername());
            statement.execute();
            statement = connection.prepareStatement("update student set student_personalNumber = ?, student_dateOfBirth =?, student_email = ?, student_mobileNumber = ?, gender_id =?, student_address =? where user_username = ?; ");
            statement.setInt(1, this.student.getPersonalNumber());
            if (datePicker.getValue() == null) {
                statement.setNull(2, Types.DATE);
            } else {
                statement.setDate(2, Date.valueOf(this.student.getDateOfBirth()));
            }
            statement.setString(3, this.student.getEmail());
            statement.setInt(4, this.student.getMobileNumber());
            if (this.student.getGender() == Gender.FEMALE) {
                statement.setInt(5, 1);
            } else if (this.student.getGender() == Gender.MALE) {
                statement.setInt(5, 2);
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, this.student.getAddress());
            statement.setString(7, this.student.getUsername());
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }
        viewPane();

    }

    public void viewCoursesPane() {
        makePaneInvisible();
        viewAllCousesPane.setVisible(true);
        courseList(new AdminController().getCourseList());
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


    private ObservableList<Course> myCourseList() {
        ObservableList<Course> courses = FXCollections.observableArrayList();
        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                    " * \n" +
                    "FROM  \n" +
                    " course c,\n" +
                    " studentrunningcourses s\n" +
                    "WHERE \n" +
                    " s.course_code = c.course_code and \n" +
                    " s.student_code = ?  ;");
            statement.setString(1,this.student.getStudentCode());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                courses.add(new Course(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4).toLocalDate(), resultSet.getInt(5), resultSet.getString(6)));
            }


            connection.close();
        }catch (SQLException e){

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

        ArrayList<String> availableCourse = new ArrayList<>();
        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                            " course_title, \n" +
                            " course_startDate, \n" +
                            " course_credit ,\n" +
                            " course_code \n" +
                            "FROM  \n" +
                            " course c   \n" +
                            " \n" +
                            "where \n" +
                            " course_startDate >  ? and \n" +
                            " course_credit < ? and\n" +
                            " not exists (select 1 from studentrunningcourses s where s.course_code = c.course_code and s.student_code = ?);"
            );
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, new StudentServices().getLeftCredit(this.student.getStudentCode()));
            System.out.println(this.student.getStudentCode());
            statement.setString(3, this.student.getStudentCode());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                availableCourse.add(resultSet.getString(1) + " " + resultSet.getDate(2) + " " + resultSet.getInt(3) + " " + resultSet.getString(4));

            }


        }catch (SQLException e){
            e.printStackTrace();
        }

//        HashMap<String, Student> readUser = null;
//        HashMap<String, Course> readCourse = null;
//
//
//        try {
//            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
//            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");
//
//        } catch (FileNotFoundException e) {
//
//        }
//
//
//        for (Map.Entry<String, Course> entry : readCourse.entrySet()) {
//            Course value = entry.getValue();
//            if (value.getStartDate().isAfter(LocalDate.now()) &&
 //                   value.getCredit() < new StudentServices().getLeftCredit(this.student.getUsername()) &&
//                    !readUser.get(this.student.getUsername()).getRunningCourses().contains(value.getCode())) {
//
//
//                availableCourse.add(value.getTittle() + " " + value.getStartDate() + " " + value.getCredit() + " " + value.getCode());
//            }
//
//        }
        return availableCourse;
    }

    public void addCourseToStudentList() {

        String course = availableCourseList.getSelectionModel().getSelectedItem().toString();
        course = course.substring(course.length() - 6);

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement("insert into studentrunningcourses (course_code, student_code) values (?,?);");
            statement.setString(1,course);
            statement.setString(2,this.student.getStudentCode());
            statement.execute();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
//
//        try {
//            HashMap<String, Student> readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
//            student = readUser.get(student.getUsername());
//            student.getRunningCourses().add(course);
//            readUser.put(student.getUsername(), student);
//            IOUtils.writeObjectToFile(readUser, "files/users");
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        registerToCoursesPane();

    }

}
