package mivs.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import mivs.courses.Course;
import mivs.db.DB;
import mivs.users.Gender;
import mivs.users.Student;
import java.sql.*;


public class StudentServices {

    public int getLeftCredit(String studentCode) {

        int credit = 0;

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                            " course_credit\n" +
                            "FROM  \n" +
                            " course c,\n" +
                            " studentrunningcourses s\n" +
                            "WHERE \n" +
                            " s.course_code = c.course_code and \n" +
                            " s.student_code = ?  ;"
            );
            statement.setString(1,studentCode);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                credit += resultSet.getInt(1);
            }

            connection.close();

        }catch (SQLException e){

        }
        return 12 - credit;

//        try {
//            readUser = (HashMap<String, Student>) IOUtils.readObjectFromFile("files/users");
//            readCourse = (HashMap<String, Course>) IOUtils.readObjectFromFile("files/courses");
//
//        } catch (FileNotFoundException e) {
//            System.out.println("No Courses Found");
//        }
//
//
//        try {
//            for (String s : readUser.get(username).getRunningCourses()) {
//
//                credit += readCourse.get(s).getCredit();
//
//            }
//
//            return 12 - credit;
//        } catch (NullPointerException e) {
//            return 12;
//        }


    }

    public ObservableList<Course> myCourseList(Student student) {
        ObservableList<Course> courses = FXCollections.observableArrayList();
        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                            " * \n" +
                            "FROM  \n" +
                            " course c,\n" +
                            " studentrunningcourses s\n" +
                            "WHERE \n" +
                            " s.course_code = c.course_code and \n" +
                            " s.student_code = ?  ;");
            statement.setString(1, student.getStudentCode());

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

    public void editStudent(Student student, DatePicker datePicker){

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement("update user set user_firstName = ? , user_secondName = ? where user_username = ?;");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getSecondName());
            statement.setString(3, student.getUsername());
            statement.execute();
            statement = connection.prepareStatement("update student set student_personalNumber = ?, student_dateOfBirth =?, student_email = ?, student_mobileNumber = ?, gender_id =?, student_address =? where user_username = ?; ");
            statement.setInt(1, student.getPersonalNumber());
            if (datePicker.getValue() == null) {
                statement.setNull(2, Types.DATE);
            } else {
                statement.setDate(2, Date.valueOf(student.getDateOfBirth()));
            }
            statement.setString(3, student.getEmail());
            statement.setInt(4, student.getMobileNumber());
            if (student.getGender() == Gender.FEMALE) {
                statement.setInt(5, 1);
            } else if (student.getGender() == Gender.MALE) {
                statement.setInt(5, 2);
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, student.getAddress());
            statement.setString(7, student.getUsername());
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }
    }

    public void insetNewStudentCourse(Student student, ListView availableCourseList){
        String course = availableCourseList.getSelectionModel().getSelectedItem().toString();
        course = course.substring(course.length() - 6);

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement("insert into studentrunningcourses (course_code, student_code) values (?,?);");
            statement.setString(1, course);
            statement.setString(2, student.getStudentCode());
            statement.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
