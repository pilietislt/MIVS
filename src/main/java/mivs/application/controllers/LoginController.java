package mivs.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mivs.back_end.Login;
import mivs.back_end.Services;
import mivs.users.Role;
import mivs.users.User;
import mivs.utils.IOUtils;
import mivs.application.alert.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class LoginController {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Button login;
    @FXML
    private Pane firstLoginPane;
    @FXML
    private  Pane loginPane;
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;



    public void login () throws IOException {

        File f = new File("files/users");
        if (f.exists() && !f.isDirectory()) {
          //  new mivs.application.alert.Alert().dd();
            secondLogin();
        } else {
            firsLogin();
        }




    }

    private void admin() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Administration panel");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);


        AdminController adminController = fxmlLoader.getController();
        adminController.init(username.getText());

        stage.show();
        Stage currentScene = (Stage) login.getScene().getWindow();
        currentScene.close();

    }
    private void student() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/student.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Student panel");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);


        StudentController studentController = fxmlLoader.getController();
        studentController.init(username.getCharacters().toString());

        stage.show();

        Stage currentScene = (Stage) login.getScene().getWindow();
        currentScene.close();

    }
    private void lecturer() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lecturer.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Lecturer panel");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);


       LecturerController lecturerController = fxmlLoader.getController();
       lecturerController.init(username.getCharacters().toString());

        stage.show();

        Stage currentScene = (Stage) login.getScene().getWindow();
        currentScene.close();

    }
    private void secondLogin() throws IOException {

        if( new Login().secondLogin(username.getText(),password.getText())){

            HashMap<String, User> readUser = null;
            try {
                readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Role role = readUser.get(username.getText()).getRole();

            switch (role){
                case STUDENT:
                    student();
                    break;
                case ADMIN:
                    admin();
                    break;
                case LECTURER:
                    lecturer();
                    break;

            }

        }else {
            new Alert().warnigAlert("Login error","Wrong username or password!!!","Please enter correct username and password");

        }

    }
    private void firsLogin() throws IOException {

        if( new Login().firsLoginFX(username.getText(),password.getText())){

            makePaneInvisible();
            firstLoginPane.setVisible(true);

        }else {
            new Alert().warnigAlert("Login error","Wrong username or password!!!","Please enter correct username and password");

        }

    }
    private void makePaneInvisible() {
        firstLoginPane.setVisible(false);
        loginPane.setVisible(false);
    }
    public void cancel(){
        makePaneInvisible();
        loginPane.setVisible(true);

    }
    public void ok () throws IOException {
        if(firstName.getText().trim().equals("")&&secondName.getText().trim().equals("")){
            new Alert().informationAlert("NO First Name/Second Name", "Please fill First Name/Second Name");



        }else {
        new Services().addFirstAdminFX(firstName.getText(),secondName.getText());
        admin();
        }
    }





}
