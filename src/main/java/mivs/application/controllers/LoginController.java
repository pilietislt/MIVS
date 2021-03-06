package mivs.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mivs.back_end.Login;
import mivs.db.DB;

import mivs.application.alert.Alert;
import mivs.services.AdminServices;


import java.io.IOException;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

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
    private Pane loginPane;
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;


    public void login() throws IOException {

        if ( new DB().ifDdExists()) {
            //System.out.println("not first login");
            secondLogin();
        } else {
            //System.out.println("first login");
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

        if (new Login().secondLoginCheck(username.getText(), password.getText())) {

            int r = 0;

            try {
                Connection con = new DB().connection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select user_role_id from user where user_username='"+username.getText()+"'");

                while (rs.next())
                    r=rs.getInt(1);

                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            switch (r) {
                case 1:
                    admin();
                    break;
                case 2:
                    lecturer();
                    break;
                case 3:
                    student();
                    break;
            }

        } else {
            new Alert().warnigAlert("Login error", "Wrong username or password!!!", "Please enter correct username and password");

        }

    }

    private void firsLogin() {

        if (new Login().firsLoginCheck(username.getText(), password.getText())) {

            makePaneInvisible();
            firstLoginPane.setVisible(true);

        } else {
            new Alert().warnigAlert("Login error", "Wrong username or password!!!", "Please enter correct username and password");

        }

    }

    private void makePaneInvisible() {
        firstLoginPane.setVisible(false);
        loginPane.setVisible(false);
    }

    public void cancel() {
        makePaneInvisible();
        loginPane.setVisible(true);

    }

    public void ok() throws IOException {
        if (firstName.getText().trim().equals("") && secondName.getText().trim().equals("")) {
            new Alert().informationAlert("NO First Name/Second Name", "Please fill First Name/Second Name");


        } else {
            new AdminServices().addFirstAdmin(firstName.getText(), secondName.getText());
            admin();
        }
    }


}
