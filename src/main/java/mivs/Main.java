package mivs;

import mivs.back_end.Login;
import mivs.db.DB;

public class Main {
    public static void main(String[] args)  {

        new DB().bdConnect();

        //new DB().newDBcreate();
        //new DB().db();




//        while (true){
//
//
//
//
//
//         new Login().login();
//        }


    }

}
