package mivs.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mivs.users.Admin;
import mivs.users.Lecturer;
import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class LecturerController extends Controller {

    @FXML
    private Label firstLable;

    private Lecturer lecturer;

    public void init(String user) {
        HashMap<String, Lecturer> readUser = null;
        try {
            readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.lecturer = readUser.get(user);
        firstLable.setText("Hello "+this.lecturer.getRole()+" "+this.lecturer.getFirstName());

    }
}
