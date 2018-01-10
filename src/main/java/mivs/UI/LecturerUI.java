package mivs.UI;

import mivs.users.Lecturer;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class LecturerUI {

    public void Menu (String username){
        HashMap<String, Lecturer> activeUser = null;

        try {
            activeUser = (HashMap<String, Lecturer> ) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello " +  activeUser.get(username).getRole()+ " "+ activeUser.get(username).getFirstName()+ " "+ activeUser.get(username).getLecturerCode());
        int choice = ScannerUtils.scanInt();
    }


}
