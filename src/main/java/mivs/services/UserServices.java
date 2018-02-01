package mivs.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mivs.db.DB;

import mivs.users.Role;
import mivs.users.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserServices {


    public ObservableList<User> getAllUserList() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            Connection connection =new DB().connection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM user ; "
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                if (resultSet.getInt(5)==1){
                    users.add( new User(resultSet.getString(1),resultSet.getString(2), Role.ADMIN,resultSet.getString(3),resultSet.getString(4)));
                }else if(resultSet.getInt(5)==2){
                    users.add( new User(resultSet.getString(1),resultSet.getString(2),Role.LECTURER,resultSet.getString(3),resultSet.getString(4)));
                }else if(resultSet.getInt(5)==3){
                    users.add( new User(resultSet.getString(1),resultSet.getString(2),Role.STUDENT,resultSet.getString(3),resultSet.getString(4)));
                }

            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
