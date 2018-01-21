package mivs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import mivs.users.Admin;
import mivs.users.Gender;
import mivs.users.Lecturer;
import mivs.users.Student;
import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class LecturerController extends Controller {

    @FXML
    private Label firstLable;
    @FXML
    private Pane startPane;
    @FXML
    private Pane viewPane;
    @FXML
    private Button updateButton;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private TextField personalNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField mobileNumber;
    @FXML
    private TextArea address;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox gender;


    private Lecturer lecturer;

    public void init(String user) {
        makePaneInvisible();
        startPane.setVisible(true);
        HashMap<String, Lecturer> readUser = null;
        try {
            readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.lecturer = readUser.get(user);
        firstLable.setText("Hello "+this.lecturer.getRole()+" "+this.lecturer.getFirstName());

    }

    public void viewPane() {

        gender.setItems(FXCollections.observableArrayList(Gender.MALE, Gender.FEMALE));
        makePaneInvisible();
        viewPane.setVisible(true);
        updateButton.setVisible(false);

        if (lecturer.getPersonalNumber() == 0) {
            personalNumber.setText("");

        } else {
            personalNumber.setText(Integer.toString(lecturer.getPersonalNumber()));
        }

        if (lecturer.getMobileNumber() == 0) {
            mobileNumber.setText("");
        } else {
            mobileNumber.setText(Integer.toString(lecturer.getMobileNumber()));
        }

        username.setText(lecturer.getUsername());
        firstName.setText(lecturer.getFirstName());
        firstName.setDisable(true);
        secondName.setText(lecturer.getSecondName());
        secondName.setDisable(true);

        personalNumber.setDisable(true);
        datePicker.setValue(lecturer.getDateOfBirth());
        datePicker.setDisable(true);
        email.setText(lecturer.getEmail());
        email.setDisable(true);

        mobileNumber.setDisable(true);
        address.setText(lecturer.getAddress());
        address.setDisable(true);
        gender.setValue(lecturer.getGender());
        gender.setDisable(true);
    }

    private void makePaneInvisible() {
        startPane.setVisible(false);
        viewPane.setVisible(false);

    }

    public void editable() {
        viewPane();
        updateButton.setVisible(true);
        firstName.setDisable(false);
        secondName.setDisable(false);
        personalNumber.setDisable(false);
        datePicker.setDisable(false);
        email.setDisable(false);
        mobileNumber.setDisable(false);
        address.setDisable(false);
        gender.setDisable(false);

        personalNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    personalNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        mobileNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mobileNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void update() {

        try {
            HashMap<String, Lecturer> readUser = (HashMap<String, Lecturer>) IOUtils.readObjectFromFile("files/users");

            lecturer = readUser.get(username.getText());
            int pNumber = 0;
            int mNumber = 0;

            if (!(personalNumber.getText().equals(""))) {
                pNumber = Integer.parseInt(personalNumber.getText());
            }
            if (!(mobileNumber.getText().trim().equals(""))) {
                mNumber = Integer.parseInt(mobileNumber.getText());
            }

            this.lecturer.setFirstName(firstName.getText());
            this.lecturer.setSecondName(secondName.getText());
            this.lecturer.setPersonalNumber(pNumber);
            this.lecturer.setDateOfBirth(datePicker.getValue());
            this.lecturer.setEmail(email.getText());
            this.lecturer.setMobileNumber(mNumber);
            this.lecturer.setAddress(address.getText());

            if (gender.getValue() == null) {

            } else if (gender.getValue().equals(Gender.MALE)) {
                this.lecturer.setGender(Gender.MALE);
            } else if (gender.getValue().equals(Gender.FEMALE)) {
                this.lecturer.setGender(Gender.FEMALE);
            }


            readUser.put(username.getText(), this.lecturer);
            IOUtils.writeObjectToFile(readUser, "files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        viewPane();

    }
}
