package mivs.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;


import mivs.users.Admin;

import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class AdminController extends Controller{


    @FXML
    private Label firstLable;

    private Admin admin;

    public void init(String user) {
        HashMap<String, Admin> readUser = null;
        try {
            readUser = (HashMap<String, Admin>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.admin = readUser.get(user);
        firstLable.setText("Hello "+this.admin.getRole()+" "+this.admin.getFirstName());

    }

}
