package mivs.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import mivs.courses.Course;
import mivs.db.DB;
import mivs.users.*;

import java.sql.*;

public class LecturerServices {


    public Lecturer getLecturer(String lecturerUsername) {
        Lecturer lecturer = null;
        try {
            Connection con = new DB().connection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT " +
                            "u.user_username, " +
                            "user_password, " +
                            "user_firstName, " +
                            "user_secondName, " +
                            "lecturer_lecturerCode, " +
                            "lecturer_personalNumber, " +
                            "lecturer_dateOfBirth , " +
                            "lecturer_email , " +
                            "lecturer_mobileNumber , " +
                            "gender_id , " +
                            "lecturer_address  " +
                            "FROM " +
                            " user u," +
                            "lecturer l " +
                            "WHERE " +
                            "l.user_username = u.user_username AND " +
                            "l.user_username ='" + lecturerUsername + "';"
            );
            while (rs.next()) {
                String userName = rs.getString(1);
                String password = rs.getString(2);
                String firstName = rs.getString(3);
                String secondName = rs.getString(4);
                String lecturerCode = rs.getString(5);
                int personalNumber = rs.getInt(6);
                String email = rs.getString(8);
                int mobileNUmber = rs.getInt(9);
                int gender = rs.getInt(10);
                String address = rs.getString(11);


                lecturer = new Lecturer(userName, password, Role.LECTURER, firstName, secondName, lecturerCode);
                lecturer.setPersonalNumber(personalNumber);
                if (rs.getDate(7) != null) {
                    lecturer.setDateOfBirth(rs.getDate(7).toLocalDate());
                }
                lecturer.setEmail(email);

                lecturer.setMobileNumber(mobileNUmber);
                lecturer.setAddress(address);
                if (gender == 1) {
                    lecturer.setGender(Gender.FEMALE);
                } else if (gender == 2) {
                    lecturer.setGender(Gender.MALE);
                }

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturer;
    }

    public ObservableList<Course> lecturerCourseList(Lecturer lecturer) {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                            " * \n" +
                            "FROM  \n" +
                            " course c,\n" +
                            " lecturerrunningcourses l\n" +
                            "WHERE \n" +
                            " l.course_code = c.course_code and \n" +
                            " lecturer_code = ?  ;");
            statement.setString(1, lecturer.getLecturerCode());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4).toLocalDate(), resultSet.getInt(5), resultSet.getString(6)));
            }


            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return courses;
    }

    public void editLecturer(Lecturer lecturer, DatePicker datePicker) {
        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement("update user set user_firstName = ? , user_secondName = ? where user_username = ?;");
            statement.setString(1, lecturer.getFirstName());
            statement.setString(2, lecturer.getSecondName());
            statement.setString(3, lecturer.getUsername());
            statement.execute();
            statement = connection.prepareStatement("update lecturer set lecturer_personalNumber = ?, lecturer_dateOfBirth =?, lecturer_email = ?, lecturer_mobileNumber = ?, gender_id =?, lecturer_address =? where user_username = ?; ");
            statement.setInt(1, lecturer.getPersonalNumber());
            if (datePicker.getValue() == null) {
                statement.setNull(2, Types.DATE);
            } else {
                statement.setDate(2, Date.valueOf(lecturer.getDateOfBirth()));
            }
            statement.setString(3, lecturer.getEmail());
            statement.setInt(4, lecturer.getMobileNumber());
            if (lecturer.getGender() == Gender.FEMALE) {
                statement.setInt(5, 1);
            } else if (lecturer.getGender() == Gender.MALE) {
                statement.setInt(5, 2);
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, lecturer.getAddress());
            statement.setString(7, lecturer.getUsername());
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }
    }


}
