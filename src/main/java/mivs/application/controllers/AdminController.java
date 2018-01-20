package mivs.application.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import mivs.back_end.Services;
import mivs.services.AdminServices;
import mivs.users.Admin;
import mivs.users.Role;
import mivs.utils.IOUtils;
import mivs.application.alert.Alert;


import java.io.FileNotFoundException;
import java.util.HashMap;


public class AdminController extends Controller {


    @FXML
    private Label firstLable;

    @FXML
    private Pane addUserPane;
    @FXML
    private Pane viewUsersPane;
    @FXML
    private Pane startPane;

    @FXML
    private ChoiceBox role;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField firstname;

    @FXML
    private TextField secondname;

    @FXML
    private TableColumn <String,String >columnFirstname;

    @FXML
    private TableColumn  <String,String >columnSecondname;

    @FXML
    private TableView tableUsers;



    private Admin admin;


    public void init(String user) {

        startPane.setVisible(true);
        addUserPane.setVisible(false);
        viewUsersPane.setVisible(false);

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

        startPane.setVisible(false);
        addUserPane.setVisible(true);
        viewUsersPane.setVisible(false);
        role.setItems(FXCollections.observableArrayList(Role.ADMIN, Role.LECTURER, Role.STUDENT));

    }

    public void viewUsersPane() {

        startPane.setVisible(false);
        addUserPane.setVisible(false);
        viewUsersPane.setVisible(true);


    }

    public void addUser(){
        if(isFiledUser()){
            if(new Services().checkUniqueBoolen(username.getText())){
                if(role.getValue().equals(Role.ADMIN)){
                    new AdminServices().addAdmin(username.getText(),password.getText(),firstname.getText(),secondname.getText());
                    new Alert().informationAlert("User added","Admin successfully added");
                    emptyUserFieleds();

                }else if(role.getValue().equals(Role.LECTURER)){
                    new AdminServices().addLecturer(username.getText(),password.getText(),firstname.getText(),secondname.getText());
                    new Alert().informationAlert("User added","Lecturer successfully added");
                    emptyUserFieleds();
                }else if(role.getValue().equals(Role.STUDENT)){
                    new AdminServices().addStudent(username.getText(),password.getText(),firstname.getText(),secondname.getText());
                    new Alert().informationAlert("User added","Student successfully added");
                    emptyUserFieleds();
                }
            }else{

                new Alert().warnigAlert("Add User error","User name already exists!!!","Please enter new username");

            }
        }

    }


    public void userlist(){

        tableUsers.setItems(FXCollections.observableArrayList("S","sss"));
        //tableUsers.getColumns().addAll(columnFirstname, columnSecondname);

    }

    public ObservableList<String> getUserList(){
        ObservableList<String> users = FXCollections.observableArrayList();
        users.add("ssda");


        return users;

    }

    private boolean isFiledUser(){
        if(username.getText().trim().isEmpty()) {
            new Alert().informationAlert("NO username", "Please fill username");
            return false;

        }else if(password.getText().trim().isEmpty()){
            new Alert().informationAlert("NO password", "Please fill password");
            return false;

        }else if(firstname.getText().trim().isEmpty()){
            new Alert().informationAlert("NO first name", "Please fill first name");
            return false;

        }else if(secondname.getText().trim().isEmpty()){
            new Alert().informationAlert("NO second name", "Please fill second name");
            return false;

        }else if(role.getSelectionModel().isEmpty()){
            new Alert().informationAlert("NO role", "Please select role");
            return false;

        }

        return true;
    }

    private void emptyUserFieleds(){
        username.clear();
        password.clear();
        firstname.clear();
        secondname.clear();
        role.getSelectionModel().clearSelection();
    }







}
