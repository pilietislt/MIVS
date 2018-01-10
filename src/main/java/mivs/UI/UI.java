package mivs.UI;

import mivs.users.User;
import mivs.utils.IOUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class UI {


    public void setActiveUser(){

        HashMap<String, User> activeUser = null;

        try {
            activeUser = (HashMap<String, User> ) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




}
