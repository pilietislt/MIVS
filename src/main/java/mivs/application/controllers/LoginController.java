package mivs.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import mivs.back_end.Login;
import mivs.users.Admin;
import mivs.users.Role;
import mivs.users.Student;
import mivs.users.User;
import mivs.utils.IOUtils;

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


    public void login () throws IOException {

        if( new Login().secondLogin(username.getCharacters().toString(),password.getCharacters().toString())){

            HashMap<String, User> readUser = null;
            try {
                readUser = (HashMap<String, User>) IOUtils.readObjectFromFile("files/users");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Role role = readUser.get(username.getCharacters().toString()).getRole();

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

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login error");
            alert.setHeaderText("Wrong username or password!!!");
            alert.setContentText("Please enter correct username and password");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });

        }


    }

    private void admin() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Administration panel");
        stage.setScene(new Scene(fxmlLoader.load()));


        AdminController adminController = fxmlLoader.getController();
        adminController.init(username.getCharacters().toString());

        stage.show();

        Stage currentScene = (Stage) login.getScene().getWindow();
        currentScene.close();

    }
    private void student() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/student.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Student panel");
        stage.setScene(new Scene(fxmlLoader.load()));


        //AdminController adminController = fxmlLoader.getController();
       // adminController.init(username.getCharacters().toString());

        stage.show();

        Stage currentScene = (Stage) login.getScene().getWindow();
        currentScene.close();

    }
    private void lecturer() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lecturer.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Lecturer panel");
        stage.setScene(new Scene(fxmlLoader.load()));


       // AdminController adminController = fxmlLoader.getController();
       // adminController.init(username.getCharacters().toString());

        stage.show();

        Stage currentScene = (Stage) login.getScene().getWindow();
        currentScene.close();

    }




}
