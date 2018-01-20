package mivs.application.alert;

import javafx.scene.control.ButtonType;

public class Alert {


    public void warnigAlert(String title,String headerText, String contentText){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
               // System.out.println("Pressed OK.");
            }
        });

    }

    public void warnigAlert(String title, String contentText){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                // System.out.println("Pressed OK.");
            }
        });

    }
    public void informationAlert(String title, String contentText){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                // System.out.println("Pressed OK.");
            }
        });

    }
}
