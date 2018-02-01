package mivs.back_end;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import mivs.db.DB;

public class Login {

    public boolean secondLoginCheck(String userName, String password) {

        try {
            Connection connection = new DB().connection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select user_password from user where user_username='" + userName + "'");
            while (rs.next()) {
                if (rs.getString(1).equals(password)) {
                    return true;
                }
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean firsLoginCheck(String userName, String password) {
        final String myUserName = "admin";
        final String myPassword = "admin";

        if (userName.equalsIgnoreCase(myUserName) && password.equals(myPassword)) {
            return true;
        }
        return false;
    }


}
