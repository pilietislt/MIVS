package mivs;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void testSetterRole_getsValue() {
        //when
        Role role = Role.ADMIN;
        //given
        role.get();
        //then
        Assert.assertEquals("Not eqouals", 1, role.get());

    }

}