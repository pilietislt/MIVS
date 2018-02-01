package mivs.services;

import mivs.db.DB;
import mivs.users.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CourseServices {

    public ArrayList<String> availableCourseList(Student student) {

        ArrayList<String> availableCourse = new ArrayList<>();
        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \n" +
                            " course_title, \n" +
                            " course_startDate, \n" +
                            " course_credit ,\n" +
                            " course_code \n" +
                            "FROM  \n" +
                            " course c   \n" +
                            " \n" +
                            "where \n" +
                            " course_startDate >  ? and \n" +
                            " course_credit < ? and\n" +
                            " not exists (select 1 from studentrunningcourses s where s.course_code = c.course_code and s.student_code = ?);"
            );
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, new StudentServices().getLeftCredit(student.getStudentCode()));
            System.out.println(student.getStudentCode());
            statement.setString(3, student.getStudentCode());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                availableCourse.add(resultSet.getString(1) + " " + resultSet.getDate(2) + " " + resultSet.getInt(3) + " " + resultSet.getString(4));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableCourse;
    }


}
