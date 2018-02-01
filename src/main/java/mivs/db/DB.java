package mivs.db;

import java.sql.*;
import java.util.ArrayList;


public class DB {

    private ArrayList<String> querys = new ArrayList<String>();


    public void mydb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mivs", "java", "java");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void createTablesforDb() {


        try {
            Statement statement = connection().createStatement();
            crateQuerys();
            for (String q : querys) {
                statement.execute(q);
            }
            connection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        fillRoleDb();
        fillGenderDb();


    }

    public void newDBcreate() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/", "java", "java");
            Statement statement = connection.createStatement();
            statement.execute("CREATE SCHEMA if not exists mivs ;");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        createTablesforDb();


    }

    public boolean ifDBexists() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mivs", "java", "java");

            connection.close();
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            return false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;


    }

    private void crateQuerys() {
        querys.add(query1);
        querys.add(query2);
        querys.add(query3);
        querys.add(query4);
        querys.add(query5);
        querys.add(query6);
        querys.add(query7);
        querys.add(query8);
        querys.add(query9);
        querys.add(query10);
        querys.add(query11);
        querys.add(query12);
     //   querys.add(query13);
        querys.add(query14);

    }

    private void fillRoleDb() {
        try {

            Statement statement = connection().createStatement();
            statement.execute("INSERT INTO role (role)VALUES ( 'ADMIN');");
            statement.execute("INSERT INTO role (role)VALUES ( 'LECTURER');");
            statement.execute("INSERT INTO role (role)VALUES ( 'STUDENT');");
            connection().close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void fillGenderDb() {
        try {

            Statement statement = connection().createStatement();
            statement.execute("INSERT INTO gender (gender)VALUES ( 'FEMALE');");
            statement.execute("INSERT INTO gender (gender)VALUES ( 'MALE');");

            connection().close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void insertUserToDb(String username, String password, String firstName, String secondName, int role) {

        try {

            Statement statement = connection().createStatement();
            statement.execute("INSERT INTO user (user_username, user_password,user_firstName, user_secondName,user_role_id)VALUES ( '" + username + "','" + password + "','" + firstName + "','" + secondName + "'," + role + ");");
            connection().close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void insertStudentToDb(String username, String password, String firstName, String secondName, int role, String studentCode) {

        try {

            Statement statement = connection().createStatement();
            statement.execute("INSERT INTO user (user_username, user_password,user_firstName, user_secondName,user_role_id)VALUES ( '" + username + "','" + password + "','" + firstName + "','" + secondName + "'," + role + ");");
            statement.execute("INSERT INTO student (user_username, student_studentCode)VALUES ( '" + username + "','" + studentCode + "');");

            connection().close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void insertLecturerToDb(String username, String password, String firstName, String secondName, int role, String lecturerCode) {

        try {

            Statement statement = connection().createStatement();
            statement.execute("INSERT INTO user (user_username, user_password,user_firstName, user_secondName,user_role_id)VALUES ( '" + username + "','" + password + "','" + firstName + "','" + secondName + "'," + role + ");");
            statement.execute("INSERT INTO lecturer (user_username, lecturer_lecturerCode)VALUES ( '" + username + "','" + lecturerCode + "');");

            connection().close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Connection connection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mivs", "java", "java");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;

    }


    String query1 = "CREATE TABLE IF NOT EXISTS user\n" +
            "(\n" +
            "    user_username VARCHAR(255) NOT NULL UNIQUE,\n" +
            "    user_password VARCHAR(255) NOT NULL,\n" +
            "    user_firstName VARCHAR(255) NOT NULL,\n" +
            "    user_secondName VARCHAR(255)NOT NULL,\n" +
            "    user_role_id INTEGER NOT NULL,\n" +
            "    CONSTRAINT user_pk PRIMARY KEY(user_username)\n" +
            ");";
    String query2 = "CREATE TABLE IF NOT EXISTS student\n" +
            "(\n" +
            "    user_username VARCHAR(255) NOT NULL UNIQUE,\n" +
            "    student_studentCode VARCHAR(255) NOT NULL UNIQUE,\n" +
            "    student_personalNumber INTEGER,\n" +
            "    student_dateOfBirth DATE,\n" +
            "    student_email VARCHAR(255),\n" +
            "    student_mobileNumber INTEGER,\n" +
            "    gender_id INTEGER,\n" +
            "    student_address VARCHAR(500),\n" +
            "    CONSTRAINT student_pk PRIMARY KEY(user_username)\n" +
            ");";
    String query3 = "CREATE TABLE IF NOT EXISTS lecturer\n" +
            "(\n" +
            "    user_username VARCHAR(255) NOT NULL UNIQUE,\n" +
            "    lecturer_lecturerCode VARCHAR(255) NOT NULL UNIQUE,\n" +
            "    lecturer_personalNumber INTEGER,\n" +
            "    lecturer_dateOfBirth DATE,\n" +
            "    lecturer_email VARCHAR(255),\n" +
            "    lecturer_mobileNumber INTEGER,\n" +
            "    gender_id INTEGER,\n" +
            "    lecturer_address VARCHAR(500),\n" +
            "    CONSTRAINT lecturer_pk PRIMARY KEY(user_username)\n" +
            ");";
    String query4 = "CREATE TABLE IF NOT EXISTS role\n" +
            "(\n" +
            "    role_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
            "    role VARCHAR(10) NOT NULL UNIQUE,\n" +
            "    CONSTRAINT role_pk PRIMARY KEY(role_id)\n" +
            ");";
    String query5 = "CREATE TABLE IF NOT EXISTS gender\n" +
            "(\n" +
            "    gender_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
            "    gender VARCHAR(10) NOT NULL UNIQUE,\n" +
            "    CONSTRAINT gender_pk PRIMARY KEY(gender_id)\n" +
            ");";
    String query6 = "CREATE TABLE IF NOT EXISTS course\n" +
            "(\n" +
            "    course_code VARCHAR(255)NOT NULL UNIQUE,\n" +
            "    course_title VARCHAR(255)NOT NULL,\n" +
            "    course_description VARCHAR(255) NOT NULL,\n" +
            "    course_startDate DATE NOT NULL,\n" +
            "    course_credit INTEGER NOT NULL,\n" +
            "    course_lecturerCode VARCHAR(255) NOT NULL,\n" +
            "    CONSTRAINT course_pk PRIMARY KEY(course_code)\n" +
            ");";
    String query7 = "CREATE TABLE IF NOT EXISTS studentRunningCourses\n" +
            "(\n" +
            "    srunningCourses_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
            "    course_code VARCHAR(255) NOT NULL,\n" +
            "    student_code VARCHAR(255) NOT NULL,\n" +
            "    CONSTRAINT studentrunningcourses_pk PRIMARY KEY(srunningCourses_id)\n" +
            ");";
    String query8 = "ALTER TABLE user\n" +
            "    ADD    FOREIGN KEY (user_role_id)\n" +
            "    REFERENCES role(role_id)\n" +
            ";";
    String query9 = "ALTER TABLE lecturer\n" +
            "    ADD    FOREIGN KEY (user_username)\n" +
            "    REFERENCES user(user_username)\n" +
            ";";
    String query10 = "ALTER TABLE lecturer\n" +
            "    ADD    FOREIGN KEY (gender_id)\n" +
            "    REFERENCES gender(gender_id)\n" +
            ";";
    String query11 = "ALTER TABLE student\n" +
            "    ADD    FOREIGN KEY (gender_id)\n" +
            "    REFERENCES gender(gender_id)\n" +
            ";";
    String query12 = "ALTER TABLE course\n" +
            "    ADD    FOREIGN KEY (course_lecturerCode)\n" +
            "    REFERENCES lecturer(lecturer_lecturerCode)\n" +
            ";";
    String query13 ="ALTER TABLE runningcourses\n"+
            "    ADD    FOREIGN KEY (course_code)\n"+
            "    REFERENCES course(course_code)\n"+
            ";";
    String query14 = "CREATE TABLE IF NOT EXISTS lecturerRunningCourses\n" +
            "(\n" +
            "    lrunningCourses_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
            "    course_code VARCHAR(255) NOT NULL,\n" +
            "    lecturer_code VARCHAR(255) NOT NULL,\n" +
            "    CONSTRAINT lecturerrunningcourses_pk PRIMARY KEY(lrunningCourses_id)\n" +
            ");";





}
