package mivs.back_end;


import java.sql.*;
import java.util.Random;
import mivs.db.DB;
import mivs.users.Role;


public class Services {

    public Boolean checkUniqueBoolean(String userName) {
        String uname = null;
        Connection connection = new DB().connection();
        try {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("select user_username from user where user_username='"+userName+"'");
            while (rs.next())
                uname = rs.getString(1);


            if (uname.equals(null)){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            return true;
        }
        return false;
    }

    public String generateCode(String firstName, String secondName, Role role) {
        Random generator = new Random();
        Integer rand1 = generator.nextInt(9);
        Integer rand2 = generator.nextInt(9);
        Integer rand3 = generator.nextInt(9);
        Integer i = role.get();
        Character a = firstName.toUpperCase().charAt(0);
        Character b = secondName.toUpperCase().charAt(0);
        String code = i.toString() + a.toString() + b.toString() + rand1.toString() + rand2.toString() + rand3.toString();
        return code;
    }


}
