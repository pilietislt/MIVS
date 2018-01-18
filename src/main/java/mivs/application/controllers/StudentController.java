package mivs.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import mivs.users.Student;
import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class StudentController extends Controller {
    @FXML
    private Label firstLable;

    private Student student;

    public void init(String user) {
        HashMap<String, Student> readUser = null;
        try {
            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.student = readUser.get(user);
        firstLable.setText("Hello " + this.student.getRole() + " " + this.student.getFirstName());

    }

}
