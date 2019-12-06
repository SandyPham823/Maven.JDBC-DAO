package daos;

import models.DTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DAOtest {

    DAO controller = new DAO();
    DTO user;

    @Before
    public void setUpTest() {
        user = new DTO();
    }

    @Test
    public void findByIdTest(){
        user = controller.findById(1);
        String actualName = "Yolande";

        Assert.assertEquals(user.getFirst_name(), actualName);
    }
}
