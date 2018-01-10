package mivs.UI;

import mivs.users.Student;
import mivs.utils.IOUtils;
import mivs.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class StudentUI {

    public void Menu (String username){
        HashMap<String, Student> activeUser = null;

        try {
            activeUser = (HashMap<String, Student> ) IOUtils.readObjectFromFile("files/users");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello " +  activeUser.get(username).getRole()+ " "+ activeUser.get(username).getFirstName()+ " "+ activeUser.get(username).getStudentCode());
        int choice = ScannerUtils.scanInt();
    }
}
