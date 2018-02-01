package mivs.services;

import mivs.db.DB;
import mivs.users.*;
import mivs.back_end.Services;
import java.sql.*;
import java.time.LocalDate;


public class AdminServices {

    public Admin getAdmin(String adminUsername){
        Admin admin = null;

        try {
            Connection con = new DB().connection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user where user_username='" + adminUsername + "'");
            while (rs.next()) {
                admin = new Admin(rs.getString(1), rs.getString(2), Role.ADMIN, rs.getString(3), rs.getString(4));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return admin;
    }

    public void addFirstAdmin(String firstName, String secondName) {
        new DB().newDbCreate();
        new DB().insertUserToDb("admin","admin",firstName,secondName,1);
    }

    public void addCourse(String title, String description, LocalDate date, int credit, String lCode) {

        String lecturerCode = codeSelectionFX(lCode);
        String code = new Services().generateCode(title, description, Role.LECTURER);

        try {
            Connection connection = new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "insert into course (course_code, course_title, course_description, course_startDate, course_credit, course_lecturerCode) values (?,?,?,?,?,?);"

            );
            statement.setString(1, code);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setDate(4, Date.valueOf(date));
            statement.setInt(5,credit);
            statement.setString(6, lecturerCode);
            statement.execute();
            statement.execute("insert into lecturerrunningcourses (course_code, lecturer_code) values ('"+code+"','"+lecturerCode+"');");

            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String codeSelectionFX(String code) {

        return code.substring(code.length() - 6);

    }

    public void addStudent(String userName, String password, String firstName, String secondName) {
        String studentCode = new Services().generateCode(firstName, secondName, Role.STUDENT);
        new StudentServices().insertStudentToDb(userName, password, firstName, secondName, 3,studentCode );

    }

    public void addLecturer(String userName, String password, String firstName, String secondName) {
        String lecturerCode = new Services().generateCode(firstName, secondName, Role.LECTURER);
        new LecturerServices().insertLecturerToDb(userName, password, firstName, secondName, 2,lecturerCode);
    }

    public void addAdmin(String userName, String password, String firstName, String secondName) {
        new DB().insertUserToDb(userName, password, firstName, secondName, 1);
    }



}
