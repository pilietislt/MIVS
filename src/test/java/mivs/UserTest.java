package mivs;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class UserTest {



    @Test
    public void testSetterFirstName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);

        //when
        user.setFirstName("magic_values");

        //then
        final Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "magic_values");
    }

    @Test
    public void testGetterFirstNme_getsValue() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);
        final Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(user, "magic_values");

        //when
        final String result = user.getFirstName();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testSetterUserName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);

        //when
        user.setUsername("magic_values");

        //then
        final Field field = user.getClass().getDeclaredField("username");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "magic_values");
    }

    @Test
    public void testGetterUserName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);
        final Field field = user.getClass().getDeclaredField("username");
        field.setAccessible(true);
        field.set(user, "magic_values");

        //when
        final String result = user.getUsername();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetterPassword_getsValue() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);
        final Field field = user.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(user, "magic_values");
        //when
        //when
        final String result = user.getPassword();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");


    }
    @Test
    public void testSetterPassword_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);

        //when
        user.setPassword("magic_values");

        //then
        final Field field = user.getClass().getDeclaredField("password");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "magic_values");
    }

    @Test
    public void testGetterSecondName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);
        final Field field = user.getClass().getDeclaredField("secondName");
        field.setAccessible(true);
        field.set(user, "magic_values");
        //when
        //when
        final String result = user.getSecondName();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");


    }
    @Test
    public void testSettersecondName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);

        //when
        user.setSecondName("magic_values");

        //then
        final Field field = user.getClass().getDeclaredField("secondName");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "magic_values");
    }

    @Test
    public void testGetterCode_getsValue() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);
        final Field field = user.getClass().getDeclaredField("code");
        field.setAccessible(true);
        field.set(user, "magic_values");
        //when
        final String result = user.getCode();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");


    }
    @Test
    public void testSetterCode_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final User user = new User(null,null,null,null,null,null);

        //when
        user.setCode("magic_values");

        //then
        final Field field = user.getClass().getDeclaredField("code");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(user), "magic_values");
    }

    @Test
    public void testGetterRole_getsValue()  {
        //given
        final User user = new User(null,null,null,null,null,null);

        //when
        final Role result = user.getRole();

        //then
        assertEquals("field wasn't retrieved properly", result, null);


    }

    @Test
    public void testSetterRole_getsValue()  {
        //given
        final User user = new User(null,null,null,null,null,null);

        //when
        user.setRole(Role.ADMIN);

        //then
        assertEquals("field wasn't retrieved properly", Role.ADMIN , user.getRole());


    }



}