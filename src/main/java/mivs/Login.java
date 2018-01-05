package mivs;

public class Login {

    public boolean login(String userName, String password) {


        final String myUserName = "admin";
        final String myPassWord = "admin";

        if (userName.equalsIgnoreCase(myUserName) && password.equals(myPassWord)) {
            //System.out.println("OK");
            return true;



        }else {
            //System.out.println("Error");
            return false;
        }
    }

}
