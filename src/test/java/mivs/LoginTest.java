package mivs;

import mivs.back_end.Login;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void giveuserlogin_whenentEnterdAdmin_thenReturnusIsTrue() {
        Login inputOutput= new Login();
        String input = "admin";
        InputStream in = new ByteArrayInputStream(input.getBytes());
      //  System.setIn(in);
        assertEquals("admin", inputOutput.loginUser());
    }

    @Test
    public void giveuserpassword_whenentEnterdAdmin_thenReturnusIsTrue() {
        Login inputOutput= new Login();
        String input = "admin";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("admin", inputOutput.loginPassword());
    }
}