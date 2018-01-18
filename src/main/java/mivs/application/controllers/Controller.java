package mivs.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Pane mainPane;

    public void logout() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(fxmlLoader.load()));


        stage.show();

        Stage currentScene = (Stage) mainPane.getScene().getWindow();
        currentScene.close();
    }

    public void close(){
        Stage currentScene = (Stage) mainPane.getScene().getWindow();
        currentScene.close();


    }
}
