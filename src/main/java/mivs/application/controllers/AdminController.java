package mivs.application.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import mivs.back_end.Services;
import mivs.courses.Course;
import mivs.services.AdminServices;
import mivs.users.Admin;
import mivs.users.Role;
import mivs.users.User;
import mivs.utils.IOUtils;
import mivs.application.alert.Alert;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class AdminController extends Controller {


    @FXML
    private Label firstLable;
    @FXML
    private Pane addUserPane;
    @FXML
    private Pane addCoursePane;
    @FXML
    private Pane viewUsersPane;
    @FXML
    private Pane viewCoursesPane;
    @FXML
    private Pane startPane;
    @FXML
    private ChoiceBox role;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField tFirstName;
    @FXML
    private TextField tSecondName;
    @FXML
    private TableColumn usernameCol;
    @FXML
    private TableColumn firstNameCol;
    @FXML
    private TableColumn secondNameCol;
    @FXML
    private TableColumn<User, Role> roleCol;
    @FXML
    private TableView tableUsers;
    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField credits;
    @FXML
    private ListView lecturerList;
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


    private Admin admin;


    public void init(String user) {

        makePaneInvisible();
        startPane.setVisible(true);

        HashMap<String, Admin> readUser = null;
        try {
            readUser = (HashMap<String, Admin>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.admin = readUser.get(user);
        firstLable.setText("Hello " + this.admin.getRole() + " " + this.admin.getFirstName());

    }

    public void addUserPane() {
        makePaneInvisible();
        addUserPane.setVisible(true);

        role.setItems(FXCollections.observableArrayList(Role.ADMIN, Role.LECTURER, Role.STUDENT));

    }

    public void viewUsersPane() {
        makePaneInvisible();
        viewUsersPane.setVisible(true);

        userList();

    }

    public void addCoursePane() {
        makePaneInvisible();
        if (new Services().getAllLecturer().size() == 0) {
            new Alert().informationAlert("No Lecturer", "Please firs add Lecturer");
            return;
        }
        addCoursePane.setVisible(true);
        lecturerList();
        //make textField accept only numeric values
        credits.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    credits.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void addUser() {
        if (isFiledUser()) {
            if (new Services().checkUniqueBoolen(username.getText())) {
                if (role.getValue().equals(Role.ADMIN)) {
                    new AdminServices().addAdmin(username.getText(), password.getText(), tFirstName.getText(), tSecondName.getText());
                    new Alert().informationAlert("User added", "Admin successfully added");
                    emptyUserFieleds();

                } else if (role.getValue().equals(Role.LECTURER)) {
                    new AdminServices().addLecturer(username.getText(), password.getText(), tFirstName.getText(), tSecondName.getText());
                    new Alert().informationAlert("User added", "Lecturer successfully added");
                    emptyUserFieleds();
                } else if (role.getValue().equals(Role.STUDENT)) {
                    new AdminServices().addStudent(username.getText(), password.getText(), tFirstName.getText(), tSecondName.getText());
                    new Alert().informationAlert("User added", "Student successfully added");
                    emptyUserFieleds();
                }
            } else {

                new Alert().warnigAlert("Add User error", "User name already exists!!!", "Please enter new username");

            }
        }

    }

    private void userList() {

        ObservableList data = getUserList();
        tableUsers.setItems(data);

        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        secondNameCol.setCellValueFactory(new PropertyValueFactory("secondName"));
        roleCol.setCellValueFactory(new PropertyValueFactory("role"));
        tableUsers.getColumns().setAll(usernameCol, firstNameCol, secondNameCol, roleCol);

    }

    private void courseList() {

        ObservableList data = getCourseList();
        coursesTable.setItems(data);

        codeCol.setCellValueFactory(new PropertyValueFactory("code"));
        titleCol.setCellValueFactory(new PropertyValueFactory("tittle"));
        startDateCol.setCellValueFactory(new PropertyValueFactory("startDate"));
        creditsCol.setCellValueFactory(new PropertyValueFactory("credit"));
        lcodeCol.setCellValueFactory(new PropertyValueFactory("lecturerCode"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        coursesTable.getColumns().setAll(codeCol, titleCol, startDateCol, creditsCol, lcodeCol, descriptionCol);

    }

    private ObservableList<User> getUserList() {
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            HashMap<String, User> readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");

            for (Map.Entry<String, User> entry : readUser.entrySet()) {
                User value = entry.getValue();
                users.add(new User(value.getUsername(), value.getPassword(), value.getRole(), value.getFirstName(), value.getSecondName()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    private ObservableList<Course> getCourseList() {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        courses.add(new Course("ddd", "ddd", "ddd", LocalDate.now(), 7, "kjklj"));


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

    private boolean isFiledUser() {
        if (username.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO username", "Please fill username");
            return false;

        } else if (password.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO password", "Please fill password");
            return false;

        } else if (tFirstName.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO first name", "Please fill first name");
            return false;

        } else if (tSecondName.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO second name", "Please fill second name");
            return false;

        } else if (role.getSelectionModel().isEmpty()) {
            new Alert().informationAlert("NO role", "Please select role");
            return false;

        }

        return true;
    }

    private void emptyUserFieleds() {
        username.clear();
        password.clear();
        tFirstName.clear();
        tSecondName.clear();
        role.getSelectionModel().clearSelection();
    }

    private void emptycourseFieleds() {
        title.clear();
        description.clear();
        credits.clear();
        datePicker.getEditor().clear();

    }

    private void makePaneInvisible() {

        startPane.setVisible(false);
        addUserPane.setVisible(false);
        viewUsersPane.setVisible(false);
        addCoursePane.setVisible(false);
        viewCoursesPane.setVisible(false);

    }

    public void addCourse() {

        if(lecturerList.getSelectionModel().getSelectedItem() == null){
            new Alert().informationAlert("NO Lecturer selected", "Please select Lecturer");
            return ;
        }else if(datePicker.getValue() == null){
            new Alert().informationAlert("NO Date selected", "Please select Start date");
            return ;

        }

        int credit = Integer.parseInt(credits.getText());
        String lCode = lecturerList.getSelectionModel().getSelectedItem().toString();
        if (isFiledCourse()) {
            new AdminServices().addCourseFX(title.getText(), description.getText(), datePicker.getValue(), credit, lCode);
            emptycourseFieleds();
            new Alert().informationAlert("Course added", title.getText()+" successfully added");



        }

    }

    private void lecturerList() {
        ObservableList<String> items = FXCollections.observableArrayList(new Services().getAllLecturer());
        lecturerList.setItems(items);
    }

    public void viewCoursesPane() {
        makePaneInvisible();
        viewCoursesPane.setVisible(true);
        courseList();
    }

    private boolean isFiledCourse() {
        if (title.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO Title", "Please fill title");
            return false;

        } else if (description.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO password", "Please fill password");
            return false;

        }  else if (credits.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO credits", "Please fill credits");
            return false;

        }

        return true;
    }


}
